import { motion } from "framer-motion";
import { Plus, Search, Edit, Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { getProductos, eliminarProducto, actualizarProducto } from "@/services/productService";
import { useState, useEffect } from "react";
import { crearProducto } from "@/services/productService";

export default function AdminInventario() {
  type Producto = {
    id: number;
    name: string;
    description: string;
    price: number;
    stock: number;
    category: string;
  };
  

  const [open, setOpen] = useState(false);
  const [inventory, setInventory] = useState<Producto[]>([]);
  const [editando, setEditando] = useState<Producto | null>(null);
  const [nuevoProducto, setNuevoProductos] = useState({
    name: "",
    description: "",
    price: 0,
    stock: 0,
  });

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

  const handleDelete = async (id: number) => {
    if (!confirm("¿Estás seguro de eliminar este producto?")) return;

    try {
      await eliminarProducto(id);
      cargarProductos();
    } catch (error) {
      console.error("Error eliminando producto:", error);
    }
  };

  const handleCreate = async () => {
    try {
      await crearProducto(nuevoProducto);
      setOpen(false);

      setNuevoProductos({
        name: "",
        description: "",
        price: 0,
        stock: 0,
      });

      cargarProductos();
    } catch (error) {
      console.error("Error creando producto:", error);
    }
  };

  const handleUpdate = async () => {
    if (!editando) return;

    try {
      await actualizarProducto(editando.id, editando);
      setEditando(null);
      cargarProductos();
    } catch (error) {
      console.error("Error actualizando producto:", error);
    }
  };

  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="font-display text-2xl font-bold text-foreground">Inventario</h1>
        <Button variant="heroFilled" onClick = {() => setOpen(true)}><Plus className="h-4 w-4 mr-2" /> Nuevo Producto</Button>
      </div>

      <div className="mb-4 relative max-w-sm">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input placeholder="Buscar producto..." className="bg-card border-border pl-10" />
      </div>

      <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="bg-card border border-border rounded-lg overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground">
                <th className="text-left p-4 font-medium">SKU</th>
                <th className="text-left p-4 font-medium">Producto</th>
                <th className="text-left p-4 font-medium">Categoría</th>
                <th className="text-left p-4 font-medium">Precio</th>
                <th className="text-left p-4 font-medium">Stock</th>
                <th className="text-left p-4 font-medium">Acciones</th>
              </tr>
            </thead>
            <tbody>
              {inventory.map((item) => (
                <tr key={item.id} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                  <td className="p-4 text-muted-foreground font-mono text-xs">{item.id}</td>
                  <td className="p-4 text-foreground font-medium">{item.name}</td>
                  <td className="p-4 text-muted-foreground">{item.category}</td>
                  <td className="p-4 text-foreground">${item.price.toLocaleString("es-CO")}</td>
                  <td className="p-4">
                    <span className={`text-xs px-2 py-1 rounded-full font-medium ${
                      item.stock <= 3 ? "bg-destructive/20 text-destructive" :
                      item.stock <= 10 ? "bg-accent/20 text-accent" :
                      "bg-green-500/20 text-green-400"
                    }`}>
                      {item.stock}
                    </span>
                  </td>
                  <td className="p-4 flex gap-1">
                    <Button variant="ghost" size="icon" onClick={() => setEditando(item)}><Edit className="h-4 w-4" /></Button>
                    <Button variant="ghost" size="icon" onClick={() => handleDelete(item.id)}><Trash2 className="h-4 w-4 text-destructive" /></Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </motion.div>
      {open && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-card p-6 rounded-lg w-[400px] border border-border">

            <h2 className="text-lg font-bold mb-4">Nuevo Producto</h2>

            <div className="space-y-3">

              <Input
                placeholder="Nombre"
                value={nuevoProducto.name}
                onChange={(e) =>
                  setNuevoProductos({ ...nuevoProducto, name: e.target.value })
                }
              />

              <Input
                placeholder="Descripción"
                value={nuevoProducto.description}
                onChange={(e) =>
                  setNuevoProductos({ ...nuevoProducto, description: e.target.value })
                }
              />

              <Input
                type="number"
                placeholder="Precio"
                value={nuevoProducto.price}
                onChange={(e) =>
                  setNuevoProductos({
                    ...nuevoProducto,
                    price: Number(e.target.value),
                  })
                }
              />

              <Input
                type="number"
                placeholder="Stock"
                value={nuevoProducto.stock}
                onChange={(e) =>
                  setNuevoProductos({
                    ...nuevoProducto,
                    stock: Number(e.target.value),
                  })
                }
              />

            </div>

            <div className="flex justify-end gap-2 mt-4">
              <Button variant="ghost" onClick={() => setOpen(false)}>
                Cancelar
              </Button>

              <Button onClick={handleCreate}>
                Guardar
              </Button>
            </div>

          </div>
        </div>
      )}

      {editando && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-card p-6 rounded-lg w-[400px] border border-border">

            <h2 className="text-lg font-bold mb-4">Editar Producto</h2>

            <div className="space-y-3">

              <Input
                value={editando.name}
                onChange={(e) =>
                  setEditando({ ...editando, name: e.target.value })
                }
              />

              <Input
                value={editando.description}
                onChange={(e) =>
                  setEditando({ ...editando, description: e.target.value })
                }
              />

              <Input
                type="number"
                value={editando.price}
                onChange={(e) =>
                  setEditando({ ...editando, price: Number(e.target.value) })
                }
              />

              <Input
                type="number"
                value={editando.stock}
                onChange={(e) =>
                  setEditando({ ...editando, stock: Number(e.target.value) })
                }
              />

            </div>

            <div className="flex justify-end gap-2 mt-4">

              <Button variant="ghost" onClick={() => setEditando(null)}>
                Cancelar
              </Button>

              <Button onClick={handleUpdate}>
                Actualizar
              </Button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
