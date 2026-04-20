import { motion } from "framer-motion";
import { Mail, ShoppingBag } from "lucide-react";
import { useEffect, useState } from "react";
import { getUsuarios, Usuario } from "@/services/userService";

export default function AdminClientes() {

  const [clients, setClients] = useState<Usuario[]>([]);

  useEffect(() => {
    getUsuarios().then((data) => { 
      console.log(data);
      const clientes = data.filter((c: Usuario) => c.rol === "CLIENTE");
      setClients(clientes);
      });
  }, []);

  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">
        Clientes
      </h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {clients.map((c, i) => (
          <motion.div
            key={c.id}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className="bg-card border border-border rounded-lg p-5 hover:border-primary/30 transition-all"
          >
            <h3 className="font-semibold text-foreground">{c.nombre}</h3>

            <div className="flex items-center gap-2 text-sm text-muted-foreground">
              <Mail className="h-3.5 w-3.5" /> {c.correo}
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
}