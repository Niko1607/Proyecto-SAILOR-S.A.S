import { motion } from "framer-motion";
import { Users } from "lucide-react";

const clients = [
  { id: 1, name: "María López", email: "maria@email.com", phone: "+57 300 123 4567", orders: 5, lastOrder: "2026-04-09" },
  { id: 2, name: "Carlos Ruiz", email: "carlos@email.com", phone: "+57 310 234 5678", orders: 3, lastOrder: "2026-04-08" },
  { id: 3, name: "Ana Gómez", email: "ana@email.com", phone: "+57 320 345 6789", orders: 8, lastOrder: "2026-04-08" },
  { id: 4, name: "Juan Pérez", email: "juan@email.com", phone: "+57 301 456 7890", orders: 2, lastOrder: "2026-04-07" },
];

export default function EmpleadoClientes() {
  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="font-display text-2xl font-bold text-foreground">Clientes</h1>
        <div className="flex items-center gap-2 text-muted-foreground text-sm">
          <Users className="h-4 w-4" />
          <span>{clients.length} clientes</span>
        </div>
      </div>
      <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="bg-card border border-border rounded-lg overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground">
                <th className="text-left p-4 font-medium">Nombre</th>
                <th className="text-left p-4 font-medium">Email</th>
                <th className="text-left p-4 font-medium">Teléfono</th>
                <th className="text-left p-4 font-medium">Pedidos</th>
                <th className="text-left p-4 font-medium">Último pedido</th>
              </tr>
            </thead>
            <tbody>
              {clients.map((c) => (
                <tr key={c.id} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                  <td className="p-4 text-foreground font-medium">{c.name}</td>
                  <td className="p-4 text-muted-foreground">{c.email}</td>
                  <td className="p-4 text-muted-foreground">{c.phone}</td>
                  <td className="p-4 text-foreground">{c.orders}</td>
                  <td className="p-4 text-muted-foreground">{c.lastOrder}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </motion.div>
    </div>
  );
}
