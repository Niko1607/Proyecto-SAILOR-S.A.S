import { motion } from "framer-motion";
import { useEffect, useState } from "react";
import { CalendarDays, ChevronDown, Package, Search, ShoppingBag, User } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
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
  const [busqueda, setBusqueda] = useState("");

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

  const ventasFiltradas = ventas.filter((venta) => {
    const texto = `${venta.id} ${venta.usuario?.nombre ?? ""} ${venta.total} ${venta.estado ? "Completado" : "Pendiente"}`.toLowerCase();
    return texto.includes(busqueda.toLowerCase());
  });

  return (
    <div className="space-y-6">
      <div className="rounded-2xl border border-border bg-card/80 backdrop-blur px-6 py-5 shadow-sm">
        <div className="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
          <div>
            <div className="flex items-center gap-2 text-primary mb-1">
              <Package className="h-5 w-5" />
              <span className="text-xs font-semibold uppercase tracking-[0.25em]">Gestión de Pedidos</span>
            </div>
            <h1 className="font-display text-3xl font-bold text-foreground">
              Panel de pedidos
            </h1>
            <p className="text-sm text-muted-foreground mt-1">
              Revisa ventas, estado y detalle de productos por pedido.
            </p>
          </div>

          <Button variant="heroFilled" className="gap-2 self-start" onClick={() => cargarVentas()}>
            <ShoppingBag className="h-4 w-4" />
            Actualizar pedidos
          </Button>
        </div>
      </div>

      <div className="relative max-w-md">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar pedido o cliente..."
          className="bg-card border-border pl-10"
          value={busqueda}
          onChange={(e) => setBusqueda(e.target.value)}
        />
      </div>

      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        className="bg-card border border-border rounded-2xl overflow-hidden shadow-sm"
      >
        <div className="overflow-x-auto">

          <table className="w-full text-sm">

            <thead>
              <tr className="border-b border-border text-muted-foreground bg-secondary/40">
                <th className="text-left p-4 font-medium">Pedido</th>
                <th className="text-left p-4 font-medium">Cliente</th>
                <th className="text-left p-4 font-medium">Total</th>
                <th className="text-left p-4 font-medium">Estado</th>
                <th className="text-left p-4 font-medium">Fecha</th>
                <th className="text-left p-4 font-medium">Productos</th>
              </tr>
            </thead>

            <tbody>

              {ventasFiltradas.map((venta) => (
                <tr key={venta.id}>
                  <td colSpan={6} className="p-0">
                    <table className="w-full text-sm">
                      <tbody>
                        <tr className="border-b border-border hover:bg-secondary/50 transition-colors">
                          <td className="p-4 text-muted-foreground font-mono text-xs">
                            #{venta.id}
                          </td>

                          <td className="p-4 text-foreground font-medium">
                            <div className="flex items-center gap-2">
                              <User className="h-4 w-4 text-muted-foreground" />
                              {venta.usuario?.nombre || "Sin usuario"}
                            </div>
                          </td>

                          <td className="p-4 text-foreground font-semibold">
                            ${venta.total.toLocaleString("es-CO")}
                          </td>

                          <td className="p-4">
                            <span
                              className={`text-xs px-2 py-1 rounded-full font-medium ${
                                venta.estado
                                  ? "bg-green-500/20 text-green-500"
                                  : "bg-accent/20 text-accent"
                              }`}
                            >
                              {venta.estado ? "Completado" : "Pendiente"}
                            </span>
                          </td>

                          <td className="p-4 text-muted-foreground text-xs">
                            <div className="flex items-center gap-1.5">
                              <CalendarDays className="h-3.5 w-3.5" />
                              N/D
                            </div>
                          </td>

                          <td className="p-4">
                            <Button
                              variant="ghost"
                              size="sm"
                              className="gap-2 text-muted-foreground hover:text-primary"
                              onClick={() => cargarDetalle(venta.id)}
                            >
                              <ChevronDown className="h-4 w-4" />
                              Ver productos
                            </Button>
                          </td>
                        </tr>

                        {detalleVentas[venta.id] && (
                          <tr className="bg-secondary/30">
                            <td colSpan={6} className="p-4">
                              <div className="rounded-xl border border-border bg-card p-4 space-y-2">
                                {detalleVentas[venta.id].map((detalle) => (
                                  <div key={detalle.id} className="flex items-center justify-between text-sm">
                                    <span className="text-foreground">{detalle.producto.nombreProducto}</span>
                                    <span className="text-muted-foreground">Cantidad: {detalle.cantidad}</span>
                                  </div>
                                ))}
                              </div>
                            </td>
                          </tr>
                        )}
                      </tbody>
                    </table>
                  </td>
                </tr>
              ))}

            </tbody>

          </table>

        </div>
      </motion.div>
    </div>
  );
}