import { motion } from "framer-motion";
import { Users } from "lucide-react";
import { useEffect, useState } from "react";
import { getUsuarios, Usuario } from "@/services/userService";

export default function EmpleadoClientes() {
  const [clients, setClients] = useState<Usuario[]>([]);
  const [loading, setLoading] = useState(false);

  const cargarClientes = async () => {
    try {
      setLoading(true);
      const data = await getUsuarios();
      setClients(data.filter((c) => c.rol === "CLIENTE"));
    } catch (error) {
      console.error("Error cargando clientes:", error);
      alert("No se pudieron cargar los clientes. Revisa la conexión con el servidor.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    cargarClientes();
  }, []);

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="font-display text-2xl font-bold text-foreground">Clientes</h1>
          <p className="text-sm text-muted-foreground">Lista actualizada de clientes registrados.</p>
        </div>
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
                <th className="text-left p-4 font-medium">Apellido</th>
                <th className="text-left p-4 font-medium">Identificación</th>
                <th className="text-left p-4 font-medium">Correo</th>
                <th className="text-left p-4 font-medium">Dirección</th>
              </tr>
            </thead>
            <tbody>
              {clients.map((c) => (
                <tr key={c.id} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                  <td className="p-4 text-foreground font-medium">{c.nombre}</td>
                  <td className="p-4 text-muted-foreground">{c.apellido}</td>
                  <td className="p-4 text-muted-foreground">{c.identificacion}</td>
                  <td className="p-4 text-foreground">{c.correo}</td>
                  <td className="p-4 text-muted-foreground">{c.direccion}</td>
                </tr>
              ))}
              {!loading && clients.length === 0 && (
                <tr>
                  <td className="p-6 text-center text-muted-foreground" colSpan={5}>
                    No hay clientes registrados.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </motion.div>
    </div>
  );
}
