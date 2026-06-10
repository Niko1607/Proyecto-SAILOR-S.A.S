import { useEffect, useState } from "react";
import { motion } from "framer-motion";
import {
  BadgeAlert,
  BadgeCheck,
  CalendarDays,
  Edit,
  Package,
  Plus,
  Search,
  Trash2,
} from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  actualizarProducto,
  crearProducto,
  eliminarProducto,
  getProductos,
  type ProductoFrontend,
} from "@/services/productService";

type ProductoForm = {
  activo: boolean;
  categoria: string;
  descripcion: string;
  fechaRegistro: string;
  imagen: string;
  name: string;
  price: number;
  stock: number;
  stockMaximo: number;
  stockMinimo: number;
};

const buildInitialForm = (): ProductoForm => ({
  activo: true,
  categoria: "Media Larga",
  descripcion: "",
  fechaRegistro: new Date().toISOString().slice(0, 10),
  imagen: "",
  name: "",
  price: 0,
  stock: 0,
  stockMaximo: 0,
  stockMinimo: 0,
});

export default function AdminInventario() {
  const [inventory, setInventory] = useState<ProductoFrontend[]>([]);
  const [search, setSearch] = useState("");
  const [createOpen, setCreateOpen] = useState(false);
  const [editando, setEditando] = useState<ProductoFrontend | null>(null);
  const [nuevoProducto, setNuevoProducto] = useState<ProductoForm>(buildInitialForm());

  useEffect(() => {
    cargarProductos();
  }, []);

  const cargarProductos = async () => {
    try {
      const data = await getProductos();
      setInventory(data);
    } catch (error) {
      console.error("Error cargando productos:", error);
    }
  };

  const resetCreateForm = () => {
    setNuevoProducto(buildInitialForm());
  };

  const handleDelete = async (id: number) => {
    if (!confirm("¿Estás seguro de eliminar este producto?")) return;

    try {
      await eliminarProducto(id);
      await cargarProductos();
    } catch (error) {
      console.error("Error eliminando producto:", error);
    }
  };

  const handleCreate = async () => {
    try {
      await crearProducto({
        id: 0,
        activo: nuevoProducto.activo,
        categoria: nuevoProducto.categoria,
        name: nuevoProducto.name,
        description: nuevoProducto.descripcion,
        price: nuevoProducto.price,
        stock: nuevoProducto.stock,
        stockMinimo: nuevoProducto.stockMinimo,
        stockMaximo: nuevoProducto.stockMaximo,
        fechaRegistro: nuevoProducto.fechaRegistro,
        imagen: nuevoProducto.imagen,
        rating: 5,
        reviews: 0,
        emoji: "🧦",
        tags: [],
        colors: [],
        sizes: [],
        details: [],
      });

      setCreateOpen(false);
      resetCreateForm();
      await cargarProductos();
    } catch (error) {
      console.error("Error creando producto:", error);
    }
  };

  const handleUpdate = async () => {
    if (!editando) return;

    try {
      await actualizarProducto(editando.id, {
        ...editando,
        tags: editando.tags ?? [],
        colors: editando.colors ?? [],
        sizes: editando.sizes ?? [],
        details: editando.details ?? [],
      });

      setEditando(null);
      await cargarProductos();
    } catch (error) {
      console.error("Error actualizando producto:", error);
    }
  };

  const productosFiltrados = inventory.filter((item) => {
    const texto = `${item.name} ${item.categoria} ${item.description}`.toLowerCase();
    return texto.includes(search.toLowerCase());
  });

  return (
    <div className="space-y-6">
      <div className="rounded-2xl border border-border bg-card/80 backdrop-blur px-6 py-5 shadow-sm">
        <div className="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
          <div>
            <div className="flex items-center gap-2 text-primary mb-1">
              <Package className="h-5 w-5" />
              <span className="text-xs font-semibold uppercase tracking-[0.25em]">
                Inventario de Productos
              </span>
            </div>
            <h1 className="font-display text-3xl font-bold text-foreground">
              Catálogo administrativo
            </h1>
            <p className="text-sm text-muted-foreground mt-1">
              Gestiona nombre, categoría, estado, fechas, imágenes y stock.
            </p>
          </div>

          <Button variant="heroFilled" onClick={() => setCreateOpen(true)} className="gap-2 self-start">
            <Plus className="h-4 w-4" />
            Nuevo Producto
          </Button>
        </div>
      </div>

      <div className="relative max-w-md">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar producto..."
          className="bg-card border-border pl-10"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>

      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        className="bg-card border border-border rounded-2xl overflow-hidden shadow-sm"
      >
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground bg-secondary/40">
                <th className="text-left p-4 font-medium">Producto</th>
                <th className="text-left p-4 font-medium">Estado</th>
                <th className="text-left p-4 font-medium">Categoría</th>
                <th className="text-left p-4 font-medium">Precio</th>
                <th className="text-left p-4 font-medium">Stock</th>
                <th className="text-left p-4 font-medium">Mín / Máx</th>
                <th className="text-left p-4 font-medium">Registro</th>
                <th className="text-left p-4 font-medium">Acciones</th>
              </tr>
            </thead>

            <tbody>
              {productosFiltrados.map((item) => (
                <tr key={item.id} className="border-b border-border hover:bg-secondary/50 transition-colors">
                  <td className="p-4">
                    <div className="font-medium text-foreground">{item.name}</div>
                    <div className="text-xs text-muted-foreground mt-1">{item.description}</div>
                    <div className="text-[11px] text-muted-foreground mt-1">
                      {item.imagen || "Sin imagen"}
                    </div>
                  </td>

                  <td className="p-4">
                    <span
                      className={`inline-flex items-center gap-1 text-xs px-2 py-1 rounded-full font-medium ${
                        item.activo
                          ? "bg-green-500/20 text-green-500"
                          : "bg-destructive/20 text-destructive"
                      }`}
                    >
                      {item.activo ? (
                        <BadgeCheck className="h-3.5 w-3.5" />
                      ) : (
                        <BadgeAlert className="h-3.5 w-3.5" />
                      )}
                      {item.activo ? "Activo" : "Inactivo"}
                    </span>
                  </td>

                  <td className="p-4 text-muted-foreground">{item.categoria || "General"}</td>

                  <td className="p-4 text-foreground">${item.price.toLocaleString("es-CO")}</td>

                  <td className="p-4">
                    <span
                      className={`text-xs px-2 py-1 rounded-full font-medium ${
                        item.stock <= 3
                          ? "bg-destructive/20 text-destructive"
                          : item.stock <= 10
                          ? "bg-accent/20 text-accent"
                          : "bg-green-500/20 text-green-400"
                      }`}
                    >
                      {item.stock}
                    </span>
                  </td>

                  <td className="p-4 text-muted-foreground text-sm">
                    {item.stockMinimo} / {item.stockMaximo}
                  </td>

                  <td className="p-4 text-muted-foreground text-xs">
                    <div className="flex items-center gap-1.5">
                      <CalendarDays className="h-3.5 w-3.5" />
                      {item.fechaRegistro}
                    </div>
                  </td>

                  <td className="p-4 flex gap-1">
                    <Button variant="ghost" size="icon" onClick={() => setEditando(item)}>
                      <Edit className="h-4 w-4" />
                    </Button>

                    <Button variant="ghost" size="icon" onClick={() => handleDelete(item.id)}>
                      <Trash2 className="h-4 w-4 text-destructive" />
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </motion.div>

      {createOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 p-4">
          <div className="w-full max-w-4xl rounded-2xl border border-border bg-card p-6 shadow-2xl max-h-[90vh] overflow-y-auto">
            <div className="mb-5">
              <h2 className="text-2xl font-bold">Nuevo Producto</h2>
              <p className="text-sm text-muted-foreground mt-1">
                Crea un producto con todos los campos solicitados.
              </p>
            </div>

            <div className="grid gap-4 md:grid-cols-2">
              <Input
                placeholder="Nombre del producto"
                value={nuevoProducto.name}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, name: e.target.value })}
              />

              <Input
                placeholder="Categoría"
                value={nuevoProducto.categoria}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, categoria: e.target.value })}
              />

              <Input
                placeholder="Descripción"
                value={nuevoProducto.descripcion}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, descripcion: e.target.value })}
              />

              <Input
                type="date"
                value={nuevoProducto.fechaRegistro}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, fechaRegistro: e.target.value })}
              />

              <Input
                placeholder="Imagen"
                value={nuevoProducto.imagen}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, imagen: e.target.value })}
              />

              <div className="flex items-center gap-2 rounded-lg border border-border px-3 py-2">
                <input
                  id="nuevo-activo"
                  type="checkbox"
                  checked={nuevoProducto.activo}
                  onChange={(e) => setNuevoProducto({ ...nuevoProducto, activo: e.target.checked })}
                />
                <label htmlFor="nuevo-activo" className="text-sm text-foreground">
                  Producto activo
                </label>
              </div>

              <Input
                type="number"
                placeholder="Precio"
                value={nuevoProducto.price}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, price: Number(e.target.value) })}
              />

              <Input
                type="number"
                placeholder="Stock"
                value={nuevoProducto.stock}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, stock: Number(e.target.value) })}
              />

              <Input
                type="number"
                placeholder="Stock mínimo"
                value={nuevoProducto.stockMinimo}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, stockMinimo: Number(e.target.value) })}
              />

              <Input
                type="number"
                placeholder="Stock máximo"
                value={nuevoProducto.stockMaximo}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, stockMaximo: Number(e.target.value) })}
              />
            </div>

            <div className="mt-6 flex justify-end gap-2">
              <Button
                variant="ghost"
                onClick={() => {
                  setCreateOpen(false);
                  resetCreateForm();
                }}
              >
                Cancelar
              </Button>
              <Button onClick={handleCreate}>Guardar</Button>
            </div>
          </div>
        </div>
      )}

      {editando && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 p-4">
          <div className="w-full max-w-4xl rounded-2xl border border-border bg-card p-6 shadow-2xl max-h-[90vh] overflow-y-auto">
            <div className="mb-5">
              <h2 className="text-2xl font-bold">Editar Producto</h2>
              <p className="text-sm text-muted-foreground mt-1">
                Ajusta los datos del producto seleccionado.
              </p>
            </div>

            <div className="grid gap-4 md:grid-cols-2">
              <Input
                value={editando.name}
                onChange={(e) => setEditando({ ...editando, name: e.target.value })}
                placeholder="Nombre del producto"
              />

              <Input
                value={editando.categoria}
                onChange={(e) => setEditando({ ...editando, categoria: e.target.value })}
                placeholder="Categoría"
              />

              <Input
                value={editando.description}
                onChange={(e) => setEditando({ ...editando, description: e.target.value })}
                placeholder="Descripción"
              />

              <Input
                type="date"
                value={editando.fechaRegistro}
                onChange={(e) => setEditando({ ...editando, fechaRegistro: e.target.value })}
              />

              <Input
                value={editando.imagen}
                onChange={(e) => setEditando({ ...editando, imagen: e.target.value })}
                placeholder="Imagen"
              />

              <div className="flex items-center gap-2 rounded-lg border border-border px-3 py-2">
                <input
                  id="editar-activo"
                  type="checkbox"
                  checked={editando.activo}
                  onChange={(e) => setEditando({ ...editando, activo: e.target.checked })}
                />
                <label htmlFor="editar-activo" className="text-sm text-foreground">
                  Producto activo
                </label>
              </div>

              <Input
                type="number"
                value={editando.price}
                onChange={(e) => setEditando({ ...editando, price: Number(e.target.value) })}
                placeholder="Precio"
              />

              <Input
                type="number"
                value={editando.stock}
                onChange={(e) => setEditando({ ...editando, stock: Number(e.target.value) })}
                placeholder="Stock"
              />

              <Input
                type="number"
                value={editando.stockMinimo}
                onChange={(e) => setEditando({ ...editando, stockMinimo: Number(e.target.value) })}
                placeholder="Stock mínimo"
              />

              <Input
                type="number"
                value={editando.stockMaximo}
                onChange={(e) => setEditando({ ...editando, stockMaximo: Number(e.target.value) })}
                placeholder="Stock máximo"
              />
            </div>

            <div className="mt-6 flex justify-end gap-2">
              <Button variant="ghost" onClick={() => setEditando(null)}>
                Cancelar
              </Button>
              <Button onClick={handleUpdate}>Actualizar</Button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
