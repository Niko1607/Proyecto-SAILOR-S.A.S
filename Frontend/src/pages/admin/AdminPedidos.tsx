import { motion } from "framer-motion";
import { useEffect, useState } from "react";
import { getVentas } from "@/services/ventaService";
import { getDetallesPorVenta } from "@/services/detalleVentaService";

type Venta = {
  id: number;
  total: number;
  estado: boolean;
  usuario?: {
    nombre: string;
  };
};

type Detalle = {
  id: number;
  cantidad: number;
  producto: {
    nombreProducto: string;
  };
};

export default function AdminPedidos() {

  const [ventas, setVentas] = useState<Venta[]>([]);
  const [detalleVentas, setDetalleVentas] = useState<Record<number, Detalle[]>>({});

  const cargarVentas = async () => {
    const data = await getVentas();
    setVentas(data);
  };

  useEffect(() => {
    cargarVentas();
  }, []);

  const cargarDetalle = async (ventaId: number) => {
    const data = await getDetallesPorVenta(ventaId);

    setDetalleVentas((prev) => ({
      ...prev,
      [ventaId]: data
    }));
  };

  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">
        Gestión de Pedidos
      </h1>

      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        className="bg-card border border-border rounded-lg overflow-hidden"
      >
        <div className="overflow-x-auto">

          <table className="w-full text-sm">

            <thead>
              <tr className="border-b border-border text-muted-foreground">
                <th className="text-left p-4 font-medium">Pedido</th>
                <th className="text-left p-4 font-medium">Cliente</th>
                <th className="text-left p-4 font-medium">Total</th>
                <th className="text-left p-4 font-medium">Estado</th>
                <th className="text-left p-4 font-medium">Productos</th>
              </tr>
            </thead>

            <tbody>

              {ventas.map((venta) => (
                <>

                  <tr key={venta.id} className="border-b">
                    <td className="p-4">{venta.id}</td>

                    <td className="p-4">
                      {venta.usuario?.nombre || "Sin usuario"}
                    </td>

                    <td className="p-4">
                      ${venta.total}
                    </td>

                    <td className="p-4">
                      {venta.estado ? "Completado" : "Pendiente"}
                    </td>

                    <td className="p-4">
                      <button
                        onClick={() => cargarDetalle(venta.id)}
                        className="bg-primary text-white px-3 py-1 rounded"
                      >
                        Ver productos
                      </button>
                    </td>
                  </tr>

                  {detalleVentas[venta.id] && (
                    <tr>
                      <td colSpan={5} className="p-4 bg-muted/40">

                        {detalleVentas[venta.id].map((detalle) => (
                          <div key={detalle.id}>
                            {detalle.producto.nombreProducto} — Cantidad: {detalle.cantidad}
                          </div>
                        ))}

                      </td>
                    </tr>
                  )}

                </>
              ))}

            </tbody>

          </table>

        </div>
      </motion.div>
    </div>
  );
}