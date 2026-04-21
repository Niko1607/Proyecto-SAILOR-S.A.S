import { useEffect, useState } from "react";
import { 
  getProductos, 
  eliminarProducto, 
  crearProducto, 
  ProductoFrontend 
} from "@/services/productService";

import { Button } from "@/components/ui/button";

export default function AdminProductos() {

  const [productos, setProductos] = useState<ProductoFrontend[]>([]);

  const [nuevoProducto, setNuevoProducto] = useState({
    name: "",
    description: "",
    price: 0,
    stock: 0,
  });

  const cargarProductos = async () => {
    try {
      const data = await getProductos();
      setProductos(data);
    } catch (error) {
      console.error("Error cargando productos:", error);
    }
  };

  useEffect(() => {
    cargarProductos();
  }, []);

  const handleEliminar = async (id: number) => {
    try {
      await eliminarProducto(id);
      cargarProductos();
    } catch (error) {
      console.error("Error eliminando producto:", error);
    }
  };

  const handleCrear = async () => {

    if (!nuevoProducto.name || !nuevoProducto.description) {
      alert("Completa todos los campos");
      return;
    }

    try {
      await crearProducto({
        ...nuevoProducto,
        id: 0,
        category: "Medias",
        rating: 5,
        reviews: 0,
        emoji: "🧦",
        tags: [],
        colors: [],
        sizes: [],
        details: [],
      });

      setNuevoProducto({
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

  return (
    <div>

      {/* FORMULARIO CREAR PRODUCTO */}
      <div className="mb-6 p-4 border rounded-lg">
        <h2 className="font-bold mb-4">Crear Producto</h2>

        <div className="grid grid-cols-4 gap-3">

          <input
            placeholder="Nombre"
            value={nuevoProducto.name}
            onChange={(e) =>
              setNuevoProducto({ ...nuevoProducto, name: e.target.value })
            }
            className="border p-2"
          />

          <input
            placeholder="Descripción"
            value={nuevoProducto.description}
            onChange={(e) =>
              setNuevoProducto({ ...nuevoProducto, description: e.target.value })
            }
            className="border p-2"
          />

          <input
            type="number"
            placeholder="Precio"
            value={nuevoProducto.price}
            onChange={(e) =>
              setNuevoProducto({
                ...nuevoProducto,
                price: Number(e.target.value),
              })
            }
            className="border p-2"
          />

          <input
            type="number"
            placeholder="Stock"
            value={nuevoProducto.stock}
            onChange={(e) =>
              setNuevoProducto({
                ...nuevoProducto,
                stock: Number(e.target.value),
              })
            }
            className="border p-2"
          />

        </div>

        <button
          onClick={handleCrear}
          className="mt-4 bg-primary text-white px-4 py-2 rounded"
        >
          Crear producto
        </button>
      </div>

      {/* TABLA PRODUCTOS */}
      <div>
        <h1 className="text-2xl font-bold mb-6">Administrar Productos</h1>

        <table className="w-full border">
          <thead>
            <tr className="border-b">
              <th className="p-3 text-left">ID</th>
              <th className="p-3 text-left">Nombre</th>
              <th className="p-3 text-left">Precio</th>
              <th className="p-3 text-left">Stock</th>
              <th className="p-3 text-left">Acciones</th>
            </tr>
          </thead>

          <tbody>
            {productos.map((p) => (
              <tr key={p.id} className="border-b">

                <td className="p-3">{p.id}</td>
                <td className="p-3">{p.name}</td>
                <td className="p-3">${p.price}</td>
                <td className="p-3">{p.stock}</td>

                <td className="p-3 flex gap-2">
                  <Button
                    variant="destructive"
                    onClick={() => handleEliminar(p.id)}
                  >
                    Eliminar
                  </Button>
                </td>

              </tr>
            ))}
          </tbody>
        </table>

      </div>

    </div>
  );
}