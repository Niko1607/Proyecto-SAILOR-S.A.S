import { motion } from "framer-motion";
import { DollarSign, ShoppingBag, Package, AlertTriangle, TrendingUp } from "lucide-react";

const stats = [
  { label: "Ventas Hoy", value: "$1,240,000", icon: DollarSign, change: "+12%" },
  { label: "Pedidos Nuevos", value: "8", icon: ShoppingBag, change: "+3" },
  { label: "Productos Activos", value: "84", icon: Package, change: "" },
  { label: "Stock Bajo", value: "3", icon: AlertTriangle, change: "Alerta" },
];

const recentOrders = [
  { id: "#1042", customer: "María López", total: "$189,000", status: "Pendiente" },
  { id: "#1041", customer: "Carlos Ruiz", total: "$328,000", status: "Empacado" },
  { id: "#1040", customer: "Ana Gómez", total: "$69,000", status: "Enviado" },
  { id: "#1039", customer: "Juan Pérez", total: "$247,000", status: "Entregado" },
];

const statusColor: Record<string, string> = {
  Pendiente: "bg-accent/20 text-accent",
  Empacado: "bg-primary/20 text-primary",
  Enviado: "bg-blue-500/20 text-blue-400",
  Entregado: "bg-green-500/20 text-green-400",
};

export default function AdminDashboard() {
  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">Dashboard</h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-8">
        {stats.map((s, i) => (
          <motion.div
            key={s.label}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className="bg-card border border-border rounded-lg p-5"
          >
            <div className="flex items-center justify-between mb-3">
              <span className="text-muted-foreground text-sm">{s.label}</span>
              <s.icon className="h-4 w-4 text-primary" />
            </div>
            <p className="text-2xl font-bold text-foreground">{s.value}</p>
            {s.change && (
              <p className="text-xs text-primary mt-1 flex items-center gap-1">
                <TrendingUp className="h-3 w-3" /> {s.change}
              </p>
            )}
          </motion.div>
        ))}
      </div>

      <div className="bg-card border border-border rounded-lg">
        <div className="p-4 border-b border-border">
          <h2 className="font-display text-lg font-bold text-foreground">Últimos Pedidos</h2>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground">
                <th className="text-left p-4 font-medium">Pedido</th>
                <th className="text-left p-4 font-medium">Cliente</th>
                <th className="text-left p-4 font-medium">Total</th>
                <th className="text-left p-4 font-medium">Estado</th>
              </tr>
            </thead>
            <tbody>
              {recentOrders.map((o) => (
                <tr key={o.id} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                  <td className="p-4 text-foreground font-medium">{o.id}</td>
                  <td className="p-4 text-foreground">{o.customer}</td>
                  <td className="p-4 text-foreground">{o.total}</td>
                  <td className="p-4">
                    <span className={`text-xs px-2.5 py-1 rounded-full font-medium ${statusColor[o.status]}`}>
                      {o.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="mt-6 bg-card border border-destructive/30 rounded-lg p-5">
        <h3 className="font-bold text-foreground flex items-center gap-2 mb-3">
          <AlertTriangle className="h-4 w-4 text-destructive" /> Alertas de Stock Bajo
        </h3>
        <ul className="space-y-2 text-sm">
          <li className="text-muted-foreground">🔴 Hoodie Negro Talla M — <span className="text-destructive font-medium">2 unidades</span></li>
          <li className="text-muted-foreground">🟡 Jean Slim Talla 30 — <span className="text-accent font-medium">5 unidades</span></li>
          <li className="text-muted-foreground">🔴 Gorra Sailor Classic — <span className="text-destructive font-medium">1 unidad</span></li>
        </ul>
      </div>
    </div>
  );
}
