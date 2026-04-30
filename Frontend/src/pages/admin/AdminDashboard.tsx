import { motion } from "framer-motion";
import { DollarSign, ShoppingBag, Package, AlertTriangle, TrendingUp } from "lucide-react";
import { useEffect, useState } from "react";

import { getProductos } from "@/services/productService";
import { getUsuarios } from "@/services/userService";
import { getVentas } from "@/services/ventaService";

export default function AdminDashboard() {
  type Usuario = {
    id: number;
    nombre: string;
    apellido: string;
    correo: string;
    password: string;
    rol: string;
    direccion: string;
  };

  const [productos, setProductos] = useState(0);
  const [usuarios, setUsuarios] = useState(0);
  const [ventas, setVentas] = useState(0);
  const [user, setUser] = useState<Usuario | null>(null);

  const cargarDatos = async () => {
    try {

      const productosData = await getProductos();
      const usuariosData = await getUsuarios();
      const ventasData = await getVentas();

      setProductos(productosData.length);
      setUsuarios(usuariosData.length);
      setVentas(ventasData.length);

    } catch (error) {
      console.error("Error cargando dashboard:", error);
    }
  };

  useEffect(() => {
    cargarDatos();
  }, []);

  useEffect(() => {
    const usuarioGuardado = localStorage.getItem("usuario");

    if (usuarioGuardado) {
      setUser(JSON.parse(usuarioGuardado));
    }
  }, []);

  const stats = [
    { label: "Ventas Totales", value: ventas, icon: DollarSign },
    { label: "Usuarios", value: usuarios, icon: ShoppingBag },
    { label: "Productos", value: productos, icon: Package },
  ];

  return (
    <div className="py-16">
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">
        Bienvenido {user?.nombre}
      </h1>
      <p className="text-muted "></p>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 mb-8">

        {stats.map((s, i) => (
          <motion.div
            key={s.label}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className="bg-card border border-border rounded-lg p-5"
          >

            <div className="flex items-center justify-between mb-3">
              <span className="text-muted-foreground text-sm">
                {s.label}
              </span>
              <s.icon className="h-4 w-4 text-primary" />
            </div>

            <p className="text-2xl font-bold text-foreground">
              {s.value}
            </p>

          </motion.div>
        ))}

      </div>

      <div className="bg-card border border-border rounded-lg p-5">
        <h2 className="font-display text-lg font-bold text-foreground mb-3">
          Panel Administrativo
        </h2>

        <p className="text-muted-foreground text-sm">
          Desde aquí puedes administrar productos, inventario, clientes y pedidos.
        </p>

      </div>

    </div>
  );
}