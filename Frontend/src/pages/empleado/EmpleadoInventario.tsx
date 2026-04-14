import { motion } from "framer-motion";
import { Package } from "lucide-react";

const products = [
  { sku: "MED-001", name: "Media Clásica Negra", stock: 120, price: "$25,000", category: "Medias" },
  { sku: "MED-002", name: "Media Malla Roja", stock: 45, price: "$35,000", category: "Medias" },
  { sku: "MED-003", name: "Media Transparente Nude", stock: 80, price: "$28,000", category: "Medias" },
  { sku: "MED-004", name: "Media Estampada Floral", stock: 30, price: "$42,000", category: "Medias" },
  { sku: "MED-005", name: "Media Compresión Sport", stock: 60, price: "$55,000", category: "Medias" },
];

export default function EmpleadoInventario() {
  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="font-display text-2xl font-bold text-foreground">Inventario</h1>
        <div className="flex items-center gap-2 text-muted-foreground text-sm">
          <Package className="h-4 w-4" />
          <span>{products.length} productos</span>
        </div>
      </div>
      <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="bg-card border border-border rounded-lg overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-border text-muted-foreground">
                <th className="text-left p-4 font-medium">SKU</th>
                <th className="text-left p-4 font-medium">Producto</th>
                <th className="text-left p-4 font-medium">Categoría</th>
                <th className="text-left p-4 font-medium">Stock</th>
                <th className="text-left p-4 font-medium">Precio</th>
              </tr>
            </thead>
            <tbody>
              {products.map((p) => (
                <tr key={p.sku} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                  <td className="p-4 text-foreground font-mono text-xs">{p.sku}</td>
                  <td className="p-4 text-foreground font-medium">{p.name}</td>
                  <td className="p-4 text-muted-foreground">{p.category}</td>
                  <td className="p-4">
                    <span className={`font-medium ${p.stock < 40 ? "text-accent" : "text-green-400"}`}>
                      {p.stock}
                    </span>
                  </td>
                  <td className="p-4 text-foreground">{p.price}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </motion.div>
    </div>
  );
}
