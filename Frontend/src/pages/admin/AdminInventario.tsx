import { motion } from "framer-motion";
import { Plus, Search, Edit, Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

const inventory = [
  { sku: "SAI-001", name: "Hoodie Premium Negro", price: 189000, stock: 2, category: "Hoodies" },
  { sku: "SAI-002", name: "Camiseta Básica Blanca", price: 69000, stock: 45, category: "Camisetas" },
  { sku: "SAI-003", name: "Jean Slim Fit Azul", price: 159000, stock: 5, category: "Jeans" },
  { sku: "SAI-004", name: "Hoodie Gris Melange", price: 179000, stock: 18, category: "Hoodies" },
  { sku: "SAI-005", name: "Camiseta Oversize Negra", price: 79000, stock: 32, category: "Camisetas" },
  { sku: "SAI-006", name: "Gorra Sailor Classic", price: 49000, stock: 1, category: "Accesorios" },
];

export default function AdminInventario() {
  return (
    <div>
      <div className="flex items-center justify-between mb-6">
        <h1 className="font-display text-2xl font-bold text-foreground">Inventario</h1>
        <Button variant="heroFilled"><Plus className="h-4 w-4 mr-2" /> Nuevo Producto</Button>
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
                <tr key={item.sku} className="border-b border-border last:border-0 hover:bg-secondary/50 transition-colors">
                  <td className="p-4 text-muted-foreground font-mono text-xs">{item.sku}</td>
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
                    <Button variant="ghost" size="icon"><Edit className="h-4 w-4" /></Button>
                    <Button variant="ghost" size="icon"><Trash2 className="h-4 w-4 text-destructive" /></Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </motion.div>
    </div>
  );
}
