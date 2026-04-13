import { motion } from "framer-motion";

const orders = [
  { id: "#1042", customer: "María López", items: 2, total: "$189,000", date: "2026-04-09", status: "Pendiente" },
  { id: "#1041", customer: "Carlos Ruiz", items: 3, total: "$328,000", date: "2026-04-08", status: "Empacado" },
  { id: "#1040", customer: "Ana Gómez", items: 1, total: "$69,000", date: "2026-04-08", status: "Enviado" },
  { id: "#1039", customer: "Juan Pérez", items: 2, total: "$247,000", date: "2026-04-07", status: "Entregado" },
];

const statusColor: Record<string, string> = {
  Pendiente: "bg-accent/20 text-accent",
  Empacado: "bg-primary/20 text-primary",
  Enviado: "bg-blue-500/20 text-blue-400",
  Entregado: "bg-green-500/20 text-green-400",
};

export default function AdminPedidos() {
  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">Gestión de Pedidos</h1>
      <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="bg-card border border-border rounded-lg overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground">
                <th className="text-left p-4 font-medium">Pedido</th>
                <th className="text-left p-4 font-medium">Cliente</th>
                <th className="text-left p-4 font-medium">Items</th>
                <th className="text-left p-4 font-medium">Total</th>
                <th className="text-left p-4 font-medium">Fecha</th>
                <th className="text-left p-4 font-medium">Estado</th>
              </tr>
            </thead>
            <tbody>
              {orders.map((o) => (
                <tr key={o.id} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors cursor-pointer">
                  <td className="p-4 text-foreground font-medium">{o.id}</td>
                  <td className="p-4 text-foreground">{o.customer}</td>
                  <td className="p-4 text-muted-foreground">{o.items}</td>
                  <td className="p-4 text-foreground">{o.total}</td>
                  <td className="p-4 text-muted-foreground">{o.date}</td>
                  <td className="p-4">
                    <span className={`text-xs px-2.5 py-1 rounded-full font-medium ${statusColor[o.status]}`}>{o.status}</span>
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
