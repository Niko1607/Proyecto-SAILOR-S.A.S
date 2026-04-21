const API_URL = "http://localhost:8081/api/productos";

// Tipo que viene del backend
type ProductoBackend = {
  id: number
  nombreProducto: string
  descripcion: string
  precioProducto: number
  stock: number
}

// Tipo completo que usa el frontend
export type ProductoFrontend = {
  id: number
  name: string
  description: string
  price: number
  originalPrice?: number
  stock: number
  category: string
  rating: number
  reviews: number
  emoji: string
  sku?: string
  tags: string[]
  colors: { name: string; value: string }[]
  sizes: string[]
  details: string[]
}

// Función para mapear backend → frontend
const mapProducto = (p: ProductoBackend): ProductoFrontend => ({
  id: p.id,
  name: p.nombreProducto,
  description: p.descripcion,
  price: p.precioProducto,
  stock: p.stock,
  category: "Medias",
  rating: 5,
  reviews: 0,
  emoji: "🧦",
  sku: `SKU-${p.id}`,
  tags: p.stock <= 5 ? ["Pocas unidades"] : [],
  colors: [
    { name: "Negro", value: "#1a1a1a" },
    { name: "Blanco", value: "#f5f5f5" },
    { name: "Gris",  value: "#9ca3af" },
  ],
  sizes: ["S", "M", "L", "XL"],
  details: [
    p.descripcion,
    `Stock disponible: ${p.stock} unidades`,
    "Material: 80% algodón, 20% elastano",
    "Lavado a máquina",
  ],
});

export const getProductos = async (): Promise<ProductoFrontend[]> => {
  const response = await fetch(API_URL);
  if (!response.ok) throw new Error("Error al obtener productos");
  const data: ProductoBackend[] = await response.json();
  return data.map(mapProducto);
};

export const getProductoById = async (id: number): Promise<ProductoFrontend> => {
  const response = await fetch(`${API_URL}/${id}`);
  if (!response.ok) throw new Error("Producto no encontrado");
  const p: ProductoBackend = await response.json();
  return mapProducto(p);
};

export const crearProducto = async (producto: ProductoFrontend) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      nombreProducto: producto.name,
      descripcion: producto.description,
      precioProducto: producto.price,
      stock: producto.stock,
    }),
  });
  if (!response.ok) throw new Error("Error al crear producto");
  return response.json();
};

export const actualizarProducto = async (id: number, producto: ProductoFrontend) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      nombreProducto: producto.name,
      descripcion: producto.description,
      precioProducto: producto.price,
      stock: producto.stock,
    }),
  });
  if (!response.ok) throw new Error("Error al actualizar producto");
  return response.json();
};

export const eliminarProducto = async (id: number) => {
  const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  if (!response.ok) throw new Error("Error al eliminar producto");
};