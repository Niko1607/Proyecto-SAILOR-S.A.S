import { motion } from "framer-motion";
import { ShoppingCart, Package } from "lucide-react";
import { Button } from "@/components/ui/button";

export default function Pedidos() {
  return (
    <div className="py-16">
      <div className="container max-w-2xl">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-12">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Tu Carrito</p>
          <h1 className="font-display text-4xl font-bold text-foreground">Pedidos</h1>
        </motion.div>

        <div className="bg-card border border-border rounded-lg p-12 text-center">
          <ShoppingCart className="h-16 w-16 text-muted-foreground mx-auto mb-4" />
          <h3 className="text-foreground font-display text-xl font-bold mb-2">Tu carrito está vacío</h3>
          <p className="text-muted-foreground text-sm mb-6">Explora nuestro catálogo y encuentra las prendas perfectas para ti.</p>
          <Button variant="heroFilled" size="lg" asChild>
            <a href="/catalogo">Ver Catálogo</a>
          </Button>
        </div>

        <div className="mt-12">
          <h2 className="font-display text-xl font-bold text-foreground mb-4 flex items-center gap-2">
            <Package className="h-5 w-5 text-primary" /> Mis Pedidos
          </h2>
          <div className="bg-card border border-border rounded-lg p-8 text-center">
            <p className="text-muted-foreground text-sm">Inicia sesión para ver tus pedidos anteriores.</p>
          </div>
        </div>
      </div>
    </div>
  );
}
