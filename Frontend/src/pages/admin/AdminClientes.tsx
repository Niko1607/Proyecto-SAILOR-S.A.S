import { motion } from "framer-motion";
import { Mail, ShoppingBag } from "lucide-react";

const clients = [
  { name: "María López", email: "maria@email.com", orders: 5, spent: "$945,000", since: "2025-01" },
  { name: "Carlos Ruiz", email: "carlos@email.com", orders: 3, spent: "$657,000", since: "2025-06" },
  { name: "Ana Gómez", email: "ana@email.com", orders: 8, spent: "$1,240,000", since: "2024-11" },
  { name: "Juan Pérez", email: "juan@email.com", orders: 2, spent: "$316,000", since: "2026-01" },
];

export default function AdminClientes() {
  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">Clientes</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {clients.map((c, i) => (
          <motion.div
            key={c.email}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className="bg-card border border-border rounded-lg p-5 hover:border-primary/30 transition-all"
          >
            <div className="flex items-center justify-between mb-3">
              <h3 className="font-semibold text-foreground">{c.name}</h3>
              <span className="text-xs text-muted-foreground">Desde {c.since}</span>
            </div>
            <div className="flex items-center gap-2 text-sm text-muted-foreground mb-2">
              <Mail className="h-3.5 w-3.5" /> {c.email}
            </div>
            <div className="flex items-center gap-4 text-sm">
              <span className="flex items-center gap-1 text-muted-foreground">
                <ShoppingBag className="h-3.5 w-3.5" /> {c.orders} pedidos
              </span>
              <span className="text-primary font-medium">{c.spent}</span>
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
}
