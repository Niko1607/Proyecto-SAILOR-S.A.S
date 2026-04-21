import { motion } from "framer-motion";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { getProductos } from "@/services/productService";
import { categories } from "@/data/products";
import { ShoppingCart, Star } from "lucide-react";

type Producto = {
  id: number;
  name: string;
  description: string;
  price: number;
  stock: number;
  category: string;
  rating: number;
  reviews: number;
  emoji: string;
  originalPrice?: number;
};

export default function Catalogo() {
  const [active, setActive] = useState("Todos");
  const [products, setProducts] = useState<Producto[]>([]);

  useEffect(() => {
    getProductos().then((data) => setProducts(data));
  }, []);

  const filtered =
    active === "Todos"
      ? products
      : products.filter((p) => p.category === active);

  return (
    <div className="py-16">
      <div className="container">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-center mb-12"
        >
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">
            Catálogo
          </p>
          <h1 className="font-display text-4xl md:text-5xl font-bold text-foreground">
            Medias para Dama
          </h1>
        </motion.div>

        <div className="flex flex-wrap justify-center gap-2 mb-10">
          {categories.map((c) => (
            <button
              key={c}
              onClick={() => setActive(c)}
              className={`px-5 py-2 text-xs tracking-wider font-semibold uppercase rounded-full border transition-all ${
                active === c
                  ? "bg-primary text-primary-foreground border-primary"
                  : "border-border text-muted-foreground hover:border-primary/50 hover:text-foreground"
              }`}
            >
              {c}
            </button>
          ))}
        </div>

        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {filtered.map((p, i) => {
            const discount = p.originalPrice
              ? Math.round(
                  ((p.originalPrice - p.price) / p.originalPrice) * 100
                )
              : 0;

            return (
              <motion.div
                key={p.id}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: i * 0.05 }}
              >
                <Link
                  to={`/producto/${p.id}`}
                  className="bg-card border border-border rounded-lg overflow-hidden group hover:border-primary/50 transition-all block"
                >
                  <div className="h-48 bg-secondary flex items-center justify-center text-5xl group-hover:scale-105 transition-transform relative">
                    {p.emoji}

                    {discount > 0 && (
                      <span className="absolute top-2 left-2 bg-primary text-primary-foreground text-[10px] font-bold px-2 py-0.5 rounded-full">
                        -{discount}%
                      </span>
                    )}

                    <div className="absolute bottom-2 right-2 opacity-0 group-hover:opacity-100 transition-opacity">
                      <div className="w-8 h-8 rounded-full bg-primary text-primary-foreground flex items-center justify-center">
                        <ShoppingCart className="h-4 w-4" />
                      </div>
                    </div>
                  </div>

                  <div className="p-4">
                    <h3 className="font-medium text-sm text-foreground truncate">
                      {p.name}
                    </h3>

                    <div className="flex items-center gap-1 mt-1">
                      <Star className="h-3 w-3 text-accent fill-accent" />
                      <span className="text-xs text-muted-foreground">
                        {p.rating} ({p.reviews})
                      </span>
                    </div>

                    <div className="flex items-baseline gap-2 mt-1">
                      <p className="text-primary font-bold">
                        ${p.price?.toLocaleString("es-CO")}
                      </p>

                      {p.originalPrice && (
                        <p className="text-muted-foreground text-xs line-through">
                          ${p.originalPrice.toLocaleString("es-CO")}
                        </p>
                      )}
                    </div>

                    <p className="text-muted-foreground text-xs mt-1">
                      {p.category}
                    </p>
                  </div>
                </Link>
              </motion.div>
            );
          })}
        </div>
      </div>
    </div>
  );
}