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

export default function AdminEmpleados() {
  const [employees, setEmployees] = useState<Usuario[]>([]);
  const [form, setForm] = useState(initialFormState);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [loading, setLoading] = useState(false);

  const cargarEmpleados = async () => {
    try {
      setLoading(true);
      const data = await getUsuarios();
      const empleados = data.filter((u) => u.rol === "EMPLEADO");
      setEmployees(empleados);
    } catch (error) {
      console.error("Error cargando empleados:", error);
      alert("No se pudieron cargar los empleados. Revisa la conexión con el servidor.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    cargarEmpleados();
  }, []);

  const resetForm = () => {
    setForm(initialFormState);
    setEditingId(null);
  };

  const handleEdit = (employee: Usuario) => {
    setEditingId(employee.id ?? null);
    setForm({
      nombre: employee.nombre ?? "",
      apellido: employee.apellido ?? "",
      identificacion: employee.identificacion ?? "",
      correo: employee.correo ?? "",
      direccion: employee.direccion ?? "",
      password: "",
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleDelete = async (id: number | undefined) => {
    if (!id) return;
    if (!confirm("¿Estás seguro de eliminar este empleado?")) {
      return;
    }

    try {
      await eliminarUsuario(id);
      cargarEmpleados();
      if (editingId === id) resetForm();
    } catch (error) {
      console.error("Error eliminando empleado:", error);
      alert("No se pudo eliminar el empleado.");
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
          rol: "EMPLEADO",
        };

        if (form.password.trim()) {
          payload.password = form.password;
        }

        await actualizarUsuario(editingId, payload);
        alert("Empleado actualizado correctamente.");
      } else {
        if (!form.password) {
          alert("La contraseña es obligatoria para crear un empleado.");
          return;
        }

        await registrarUsuario({
          nombre: form.nombre,
          apellido: form.apellido,
          identificacion: form.identificacion,
          correo: form.correo,
          password: form.password,
          rol: "EMPLEADO",
          direccion: form.direccion,
        });

        alert("Empleado creado correctamente.");
      }

      resetForm();
      cargarEmpleados();
    } catch (error) {
      console.error("Error guardando empleado:", error);
      alert("No se pudo guardar el empleado. Revisa los datos.");
    }
  };

  return (
    <div>
      <div className="mb-6 p-4 border rounded-lg bg-card">
        <div className="flex flex-col gap-4 md:flex-row md:items-center md:justify-between mb-4">
          <div>
            <h1 className="font-display text-2xl font-bold text-foreground">
              {editingId ? "Editar empleado" : "Crear empleado"}
            </h1>
            <p className="text-sm text-muted-foreground">
              {editingId
                ? "Actualiza los datos del empleado y guarda los cambios."
                : "Registra un empleado nuevo en el sistema."}
            </p>
          </div>
          <div className="flex items-center gap-2 text-muted-foreground text-sm">
            <Users className="h-4 w-4" />
            <span>{employees.length} empleados</span>
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
            placeholder="Identificación"
            value={form.identificacion}
            onChange={(e) => setForm({ ...form, identificacion: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary"
          />

          <input
            placeholder="Correo electrónico"
            type="email"
            value={form.correo}
            onChange={(e) => setForm({ ...form, correo: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary"
          />

          <input
            placeholder="Dirección"
            value={form.direccion}
            onChange={(e) => setForm({ ...form, direccion: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary md:col-span-2"
          />

          <input
            placeholder={editingId ? "Dejar en blanco para no cambiar contraseña" : "Contraseña"}
            type="password"
            value={form.password}
            onChange={(e) => setForm({ ...form, password: e.target.value })}
            className="border border-border rounded-lg px-3 py-2 bg-secondary md:col-span-2"
          />

          <div className="flex flex-wrap items-center gap-2 md:col-span-2">
            <Button type="submit" variant="heroFilled" className="grow">
              {editingId ? "Actualizar empleado" : "Crear empleado"}
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
              <th className="p-3">Identificación</th>
              <th className="p-3">Correo</th>
              <th className="p-3">Dirección</th>
              <th className="p-3">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => (
              <tr key={employee.id} className="border-b border-border hover:bg-secondary/50 transition-colors">
                <td className="p-3">{employee.id}</td>
                <td className="p-3">{employee.nombre}</td>
                <td className="p-3">{employee.apellido}</td>
                <td className="p-3">{employee.identificacion}</td>
                <td className="p-3">{employee.correo}</td>
                <td className="p-3">{employee.direccion}</td>
                <td className="p-3 flex flex-wrap gap-2">
                  <Button type="button" variant="secondary" onClick={() => handleEdit(employee)}>
                    <Pencil className="h-4 w-4" /> Editar
                  </Button>
                  <Button type="button" variant="destructive" onClick={() => handleDelete(employee.id)}>
                    <Trash2 className="h-4 w-4" /> Eliminar
                  </Button>
                </td>
              </tr>
            ))}
            {!loading && employees.length === 0 && (
              <tr>
                <td className="p-6 text-center text-muted-foreground" colSpan={7}>
                  No hay empleados registrados.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
