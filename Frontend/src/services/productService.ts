const API_URL = "http://localhost:8081/api/productos";

type Producto = {
  id: number
  NombreProducto: string
  descripcion: string
  precioProducto: number
  stock: number
}

type ProductoFrontend = {
  name: string
  description: string
  price: number
  stock: number
}

export const getProductos = async () => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener productos");
  }

  const data : Producto[] = await response.json()

  return data.map((p) => ({
    id: p.id,
    name: p.NombreProducto,
    description: p.descripcion,
    price: p.precioProducto,
    stock: p.stock,
    category: "Medias", 
    rating: 5,
    reviews: 0,
    emoji: "🧦"
  }))
}
  

export const getProductoById = async (id: number) => {
  const response = await fetch(`${API_URL}/${id}`);
  const p = await response.json();
  return {
    id: p.id,
    name: p.nombreProducto,
    description: p.descripcion,
    price: p.precioProducto,
    stock: p.stock,
    category: "Medias", 
    rating: 5,
    reviews: 0,
    emoji: "🧦"
  }
};

export const crearProducto = async (producto: ProductoFrontend) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nombreProducto: producto.name,
      descripcion: producto.description,
      precioProducto: producto.price,
      stock: producto.stock,
    }),
  });

  return response.json();
};

export const eliminarProducto = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });
};

export const actualizarProducto = async (id: number, producto: ProductoFrontend) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nombreProducto: producto.name,
      descripcion: producto.description,
      precioProducto: producto.price,
      stock: producto.stock,
    }),
  });

  return response.json();
};