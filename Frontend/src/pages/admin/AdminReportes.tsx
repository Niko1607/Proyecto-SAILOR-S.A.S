import { motion } from "framer-motion";
import { BarChart3, TrendingUp, Award } from "lucide-react";

const topProducts = [
  { name: "Hoodie Premium Negro", sold: 142, revenue: "$26,838,000" },
  { name: "Camiseta Básica Blanca", sold: 98, revenue: "$6,762,000" },
  { name: "Jean Slim Fit Azul", sold: 76, revenue: "$12,084,000" },
];

export default function AdminReportes() {
  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">Reportes</h1>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="bg-card border border-border rounded-lg p-6">
          <h2 className="font-display text-lg font-bold text-foreground flex items-center gap-2 mb-4">
            <Award className="h-5 w-5 text-primary" /> Productos Más Vendidos
          </h2>
          <div className="space-y-4">
            {topProducts.map((p, i) => (
              <div key={p.name} className="flex items-center justify-between">
                <div className="flex items-center gap-3">
                  <span className="text-primary font-bold text-lg">#{i + 1}</span>
                  <div>
                    <p className="text-foreground font-medium text-sm">{p.name}</p>
                    <p className="text-muted-foreground text-xs">{p.sold} unidades</p>
                  </div>
                </div>
                <span className="text-foreground font-medium text-sm">{p.revenue}</span>
              </div>
            ))}
          </div>
        </motion.div>

        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: 0.1 }} className="bg-card border border-border rounded-lg p-6">
          <h2 className="font-display text-lg font-bold text-foreground flex items-center gap-2 mb-4">
            <TrendingUp className="h-5 w-5 text-primary" /> Resumen Mensual
          </h2>
          <div className="space-y-4">
            <div className="flex justify-between items-center">
              <span className="text-muted-foreground text-sm">Ventas totales</span>
              <span className="text-foreground font-bold">$45,684,000</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-muted-foreground text-sm">Pedidos completados</span>
              <span className="text-foreground font-bold">316</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-muted-foreground text-sm">Ticket promedio</span>
              <span className="text-foreground font-bold">$144,570</span>
            </div>
            <div className="flex justify-between items-center">
              <span className="text-muted-foreground text-sm">Nuevos clientes</span>
              <span className="text-foreground font-bold">42</span>
            </div>
          </div>
        </motion.div>

        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: 0.2 }} className="bg-card border border-border rounded-lg p-6 lg:col-span-2">
          <h2 className="font-display text-lg font-bold text-foreground flex items-center gap-2 mb-4">
            <BarChart3 className="h-5 w-5 text-primary" /> Auditoría de Movimientos
          </h2>
          <div className="space-y-3 text-sm">
            <div className="flex items-center gap-3 text-muted-foreground">
              <span className="text-xs text-muted-foreground w-32">Hoy 14:30</span>
              <span className="text-foreground">Admin</span> agregó 20 unidades de Hoodie Premium Negro
            </div>
            <div className="flex items-center gap-3 text-muted-foreground">
              <span className="text-xs text-muted-foreground w-32">Hoy 11:15</span>
              <span className="text-foreground">Sistema</span> descontó 1 unidad de Gorra Sailor (Pedido #1042)
            </div>
            <div className="flex items-center gap-3 text-muted-foreground">
              <span className="text-xs text-muted-foreground w-32">Ayer 16:00</span>
              <span className="text-foreground">Empleado1</span> editó precio de Jean Slim Fit
            </div>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
