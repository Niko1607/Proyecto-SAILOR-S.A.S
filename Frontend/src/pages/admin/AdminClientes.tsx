import { motion } from "framer-motion";
import { Pencil, Trash2, Users } from "lucide-react";
import { useEffect, useState, FormEvent } from "react";
import { Button } from "@/components/ui/button";
import {
  getUsuarios,
  registrarUsuario,
  actualizarUsuario,
  eliminarUsuario,
  Usuario,
} from "@/services/userService";

const initialFormState = {
  nombre: "",
  apellido: "",
  identificacion: "",
  correo: "",
  direccion: "",
  password: "",
};

export default function AdminClientes() {
  const [clients, setClients] = useState<Usuario[]>([]);
  const [form, setForm] = useState(initialFormState);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [loading, setLoading] = useState(false);

  const cargarClientes = async () => {
    try {
      setLoading(true);
      const data = await getUsuarios();
      const clientes = data.filter((c) => c.rol === "CLIENTE");
      setClients(clientes);
    } catch (error) {
      console.error("Error cargando clientes:", error);
      alert("No se pudieron cargar los clientes. Revisa la conexi�n con el servidor.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    cargarClientes();
  }, []);

  const resetForm = () => {
    setForm(initialFormState);
    setEditingId(null);
  };

  const handleEdit = (client: Usuario) => {
    setEditingId(client.id ?? null);
    setForm({
      nombre: client.nombre ?? "",
      apellido: client.apellido ?? "",
      identificacion: client.identificacion ?? "",
      correo: client.correo ?? "",
      direccion: client.direccion ?? "",
      password: "",
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleDelete = async (id: number | undefined) => {
    if (!id) return;
    if (!confirm("�Est�s seguro de eliminar este cliente?")) {
      return;
    }

    try {
      await eliminarUsuario(id);
      cargarClientes();
      if (editingId === id) resetForm();
    } catch (error) {
      console.error("Error eliminando cliente:", error);
      alert("No se pudo eliminar el cliente.");
    }
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!form.nombre || !form.apellido || !form.identificacion || !form.correo || !form.direccion) {
      alert("Por favor completa todos los campos obligatorios.");
      return;
    }

    try {
      if (editingId) {
        const payload: Partial<Usuario> = {
          nombre: form.nombre,
          apellido: form.apellido,
          identificacion: form.identificacion,
          correo: form.correo,
          direccion: form.direccion,
          rol: "CLIENTE",
        };

        if (form.password.trim()) {
          payload.password = form.password;
        }

        await actualizarUsuario(editingId, payload);
        alert("Cliente actualizado correctamente.");
      } else {
        if (!form.password) {
          alert("La contrase�a es obligatoria para crear un cliente.");
          return;
        }

        await registrarUsuario({
          nombre: form.nombre,
          apellido: form.apellido,
          identificacion: form.identificacion,
          correo: form.correo,
          password: form.password,
          rol: "CLIENTE",
          direccion: form.direccion,
        });

        alert("Cliente creado correctamente.");
      }

      resetForm();
      cargarClientes();
    } catch (error) {
      console.error("Error guardando cliente:", error);
      alert("No se pudo guardar el cliente. Revisa los datos.");
    }
  };

  return (
    <div>
      <div className="mb-6 p-4 border rounded-lg bg-card">
        <div className="flex flex-col gap-4 md:flex-row md:items-center md:justify-between mb-4">
          <div>
            <h1 className="font-display text-2xl font-bold text-foreground">
              {editingId ? "Editar cliente" : "Crear cliente"}
            </h1>
            <p className="text-sm text-muted-foreground">
              {editingId
                ? "Actualiza los datos del cliente y guarda los cambios."
                : "Registra un cliente nuevo en el sistema."}
            </p>
          </div>
          <div className="flex items-center gap-2 text-muted-foreground text-sm">
            <Users className="h-4 w-4" />
            <span>{clients.length} clientes</span>
          </div>
        </div>

        <form onSubmit={handleSubmit} className="grid gap-4 md:grid-cols-2">
          <input
            placeholder="Nombre"
            value={form.nombre}
            onChange={(e) => setForm({ ...form, nombre: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary"
          />

          <input
            placeholder="Apellido"
            value={form.apellido}
            onChange={(e) => setForm({ ...form, apellido: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary"
          />

          <input
            placeholder="Identificaci�n"
            value={form.identificacion}
            onChange={(e) => setForm({ ...form, identificacion: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary"
          />

          <input
            placeholder="Correo electr�nico"
            type="email"
            value={form.correo}
            onChange={(e) => setForm({ ...form, correo: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary"
          />

          <input
            placeholder="Direcci�n"
            value={form.direccion}
            onChange={(e) => setForm({ ...form, direccion: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary md:col-span-2"
          />

          <input
            placeholder={editingId ? "Dejar en blanco para no cambiar contrase�a" : "Contrase�a"}
            type="password"
            value={form.password}
            onChange={(e) => setForm({ ...form, password: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary md:col-span-2"
          />

          <div className="flex flex-wrap items-center gap-2 md:col-span-2">
            <Button type="submit" variant="heroFilled" className="grow">
              {editingId ? "Actualizar cliente" : "Crear cliente"}
            </Button>
            {editingId && (
              <Button type="button" variant="secondary" onClick={resetForm}>
                Cancelar
              </Button>
            )}
          </div>
        </form>
      </div>

      <div className="bg-card border border-border rounded-lg overflow-x-auto">
        <table className="w-full text-left text-sm">
          <thead className="bg-secondary text-muted-foreground">
            <tr>
              <th className="p-3">ID</th>
              <th className="p-3">Nombre</th>
              <th className="p-3">Apellido</th>
              <th className="p-3">Identificaci�n</th>
              <th className="p-3">Correo</th>
              <th className="p-3">Direcci�n</th>
              <th className="p-3">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {clients.map((client) => (
              <tr key={client.id} className="border-b border-border hover:bg-secondary/50 transition-colors">
                <td className="p-3">{client.id}</td>
                <td className="p-3">{client.nombre}</td>
                <td className="p-3">{client.apellido}</td>
                <td className="p-3">{client.identificacion}</td>
                <td className="p-3">{client.correo}</td>
                <td className="p-3">{client.direccion}</td>
                <td className="p-3 flex flex-wrap gap-2">
                  <Button type="button" variant="secondary" onClick={() => handleEdit(client)}>
                    <Pencil className="h-4 w-4" /> Editar
                  </Button>
                  <Button type="button" variant="destructive" onClick={() => handleDelete(client.id)}>
                    <Trash2 className="h-4 w-4" /> Eliminar
                  </Button>
                </td>
              </tr>
            ))}
            {!loading && clients.length === 0 && (
              <tr>
                <td className="p-6 text-center text-muted-foreground" colSpan={7}>
                  No hay clientes registrados.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
