import { motion } from "framer-motion";
import { BarChart3, TrendingUp, Award, AlertTriangle } from "lucide-react";
import { useEffect, useState } from "react";

import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from "recharts";

import { getVentas } from "@/services/ventaService";
import { getProductos, ProductoFrontend } from "@/services/productService";
import { getUsuarios } from "@/services/userService";

type Venta = {
  id: number;
  total: number;
  fecha: string;
  productos: {
    productoId: number;
    cantidad: number;
  }[];
};

type Usuario = {
  id: number;
  rol: string;
};

type GraficaVentas = {
  mes: string;
  ventas: number;
};

export default function AdminReportes() {

  const [ventas, setVentas] = useState(0);
  const [pedidos, setPedidos] = useState(0);
  const [ticketPromedio, setTicketPromedio] = useState(0);
  const [clientes, setClientes] = useState(0);

  const [ventasGrafica, setVentasGrafica] = useState<GraficaVentas[]>([]);
  const [topProductos, setTopProductos] = useState<ProductoFrontend[]>([]);
  const [stockBajo, setStockBajo] = useState<ProductoFrontend[]>([]);

  const cargarDatos = async () => {

    try {

      const ventasData: Venta[] = await getVentas();
      const productosData: ProductoFrontend[] = await getProductos();
      const usuariosData: Usuario[] = await getUsuarios();

      // ---------------------
      // RESUMEN GENERAL
      // ---------------------

      const totalVentas = ventasData.reduce(
        (sum, v) => sum + (v.total || 0),
        0
      );

      const totalPedidos = ventasData.length;

      const promedio =
        totalPedidos > 0 ? totalVentas / totalPedidos : 0;

      const nuevosClientes = usuariosData.filter(
        (u) => u.rol === "CLIENTE"
      ).length;

      setVentas(totalVentas);
      setPedidos(totalPedidos);
      setTicketPromedio(promedio);
      setClientes(nuevosClientes);

      // ---------------------
      // VENTAS POR MES
      // ---------------------

      const ventasPorMes: Record<string, number> = {};

      ventasData.forEach((v) => {

        const fecha = new Date(v.fecha);
        const mes = fecha.toLocaleString("es-CO", { month: "short" });

        if (!ventasPorMes[mes]) {
          ventasPorMes[mes] = 0;
        }

        ventasPorMes[mes] += v.total;

      });

      const dataGrafica = Object.keys(ventasPorMes).map((mes) => ({
        mes,
        ventas: ventasPorMes[mes]
      }));

      setVentasGrafica(dataGrafica);

      // ---------------------
      // TOP PRODUCTOS
      // ---------------------

      const top = [...productosData]
        .slice(0, 5);

      setTopProductos(top);

      // ---------------------
      // STOCK BAJO
      // ---------------------

      const alertas = productosData.filter(
        (p) => p.stock < 10
      );

      setStockBajo(alertas);

    } catch (error) {
      console.error("Error cargando reportes:", error);
    }
  };

  useEffect(() => {
    cargarDatos();
  }, []);

  return (
    <div>

      <h1 className="font-display text-2xl font-bold text-foreground mb-6">
        Dashboard de Reportes
      </h1>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">

        {/* RESUMEN */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="bg-card border border-border rounded-lg p-6"
        >

          <h2 className="font-bold flex items-center gap-2 mb-4">
            <TrendingUp className="h-5 w-5 text-primary"/>
            Resumen General
          </h2>

          <div className="space-y-3">

            <div className="flex justify-between">
              <span>Ventas</span>
              <span className="font-bold">
                ${ventas.toLocaleString("es-CO")}
              </span>
            </div>

            <div className="flex justify-between">
              <span>Pedidos</span>
              <span className="font-bold">{pedidos}</span>
            </div>

            <div className="flex justify-between">
              <span>Ticket promedio</span>
              <span className="font-bold">
                ${ticketPromedio.toLocaleString("es-CO")}
              </span>
            </div>

            <div className="flex justify-between">
              <span>Clientes</span>
              <span className="font-bold">{clientes}</span>
            </div>

          </div>

        </motion.div>

        {/* GRAFICA */}
         <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
          className="bg-card border border-border rounded-lg p-6"
        >

          <h2 className="font-bold flex items-center gap-2 mb-4">
            <BarChart3 className="h-5 w-5 text-primary"/>
            Ventas por Mes
          </h2>

          <ResponsiveContainer width="100%" height={260}>

            <BarChart data={ventasGrafica}>

              <XAxis dataKey="mes"/>

              <YAxis/>

              <Tooltip/>

              <Bar
                dataKey="ventas"
                fill="#3b82f6"
                radius={[4,4,0,0]}
              />

            </BarChart>

          </ResponsiveContainer>

        </motion.div>

        {/* TOP PRODUCTOS */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
          className="bg-card border border-border rounded-lg p-6"
        >

          <h2 className="font-bold flex items-center gap-2 mb-4">
            <Award className="h-5 w-5 text-primary"/>
            Productos Más Vendidos
          </h2>

          <div className="space-y-3">

            {topProductos.map((p, i) => (

              <div
                key={p.id}
                className="flex justify-between text-sm"
              >

                <span>
                  #{i+1} {p.name}
                </span>

                <span className="font-bold">
                  Stock: {p.stock}
                </span>

              </div>

            ))}

          </div>

        </motion.div>

        {/* ALERTAS */}
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
          className="bg-card border border-border rounded-lg p-6"
        >

          <h2 className="font-bold flex items-center gap-2 mb-4">
            <AlertTriangle className="h-5 w-5 text-yellow-500"/>
            Stock Bajo
          </h2>

          <div className="space-y-2 text-sm">

            {stockBajo.length === 0 && (
              <p>No hay productos con stock bajo</p>
            )}

            {stockBajo.map((p) => (

              <div
                key={p.id}
                className="flex justify-between"
              >

                <span>{p.name}</span>

                <span className="text-red-500 font-bold">
                  {p.stock}
                </span>

              </div>

            ))}

          </div>

        </motion.div>

      </div>

    </div>

  );

}