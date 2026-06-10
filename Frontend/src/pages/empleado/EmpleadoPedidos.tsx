import { motion } from "framer-motion";
import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Eye, ChevronDown, CalendarDays, Package, Search, User } from "lucide-react";
import { Input } from "@/components/ui/input";

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

const allStatuses = ["Pendiente", "Empacado", "Enviado", "Entregado"];

export default function EmpleadoPedidos() {
  const [orderStatuses, setOrderStatuses] = useState<Record<string, string>>(
    Object.fromEntries(orders.map((o) => [o.id, o.status]))
  );
  const [search, setSearch] = useState("");

  const handleStatusChange = (orderId: string, newStatus: string) => {
    setOrderStatuses((prev) => ({ ...prev, [orderId]: newStatus }));
  };

  const filteredOrders = orders.filter((order) => {
    const text = `${order.id} ${order.customer} ${order.status}`.toLowerCase();
    return text.includes(search.toLowerCase());
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
            <h1 className="font-display text-3xl font-bold text-foreground">Panel de pedidos</h1>
            <p className="text-sm text-muted-foreground mt-1">Controla el estado de cada pedido y revisa sus detalles.</p>
          </div>
        </div>
      </div>

      <div className="relative max-w-md">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar pedido o cliente..."
          className="bg-card border-border pl-10"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="bg-card border border-border rounded-2xl overflow-hidden shadow-sm">
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground bg-secondary/40">
                <th className="text-left p-4 font-medium">Pedido</th>
                <th className="text-left p-4 font-medium">Cliente</th>
                <th className="text-left p-4 font-medium">Items</th>
                <th className="text-left p-4 font-medium">Total</th>
                <th className="text-left p-4 font-medium">Fecha</th>
                <th className="text-left p-4 font-medium">Estado</th>
                <th className="text-left p-4 font-medium">Acción</th>
              </tr>
            </thead>
            <tbody>
              {filteredOrders.map((o) => {
                const currentStatus = orderStatuses[o.id];
                return (
                  <tr key={o.id} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                    <td className="p-4 text-muted-foreground font-mono text-xs">{o.id}</td>
                    <td className="p-4 text-foreground font-medium">
                      <div className="flex items-center gap-2">
                        <User className="h-4 w-4 text-muted-foreground" />
                        {o.customer}
                      </div>
                    </td>
                    <td className="p-4 text-muted-foreground">{o.items}</td>
                    <td className="p-4 text-foreground">{o.total}</td>
                    <td className="p-4 text-muted-foreground text-xs">
                      <div className="flex items-center gap-1.5">
                        <CalendarDays className="h-3.5 w-3.5" />
                        {o.date}
                      </div>
                    </td>
                    <td className="p-4">
                      <select
                        value={currentStatus}
                        onChange={(e) => handleStatusChange(o.id, e.target.value)}
                        className={`text-xs px-2.5 py-1 rounded-full font-medium border-0 cursor-pointer ${statusColor[currentStatus]} bg-opacity-20`}
                      >
                        {allStatuses.map((s) => (
                          <option key={s} value={s}>{s}</option>
                        ))}
                      </select>
                    </td>
                    <td className="p-4">
                      <Button variant="ghost" size="sm" className="gap-1 text-muted-foreground hover:text-primary">
                        <Eye className="h-4 w-4" /> Ver
                      </Button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </motion.div>
    </div>
  );
}
