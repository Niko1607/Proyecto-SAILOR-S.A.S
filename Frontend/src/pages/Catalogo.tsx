import { motion } from "framer-motion";
import { useState } from "react";

const categories = ["Todos", "Hasta la Cadera", "Pantimedias", "Compresión", "Con Diseño"];

const products = [
  { id: 1, name: "Media Cadera Clásica Negra", category: "Hasta la Cadera", price: 45000, color: "🖤" },
  { id: 2, name: "Media Cadera Roja Pasión", category: "Hasta la Cadera", price: 48000, color: "❤️" },
  { id: 3, name: "Media Cadera Nude Natural", category: "Hasta la Cadera", price: 42000, color: "🤎" },
  { id: 4, name: "Pantimedia Transparente Negra", category: "Pantimedias", price: 35000, color: "🖤" },
  { id: 5, name: "Pantimedia Beige Elegante", category: "Pantimedias", price: 35000, color: "🤍" },
  { id: 6, name: "Media Compresión Graduada", category: "Compresión", price: 65000, color: "🩶" },
  { id: 7, name: "Media Cadera Encaje Blanco", category: "Con Diseño", price: 55000, color: "🤍" },
  { id: 8, name: "Media Cadera Diseño Floral", category: "Con Diseño", price: 58000, color: "🩷" },
  { id: 9, name: "Pantimedia Control Abdomen", category: "Pantimedias", price: 52000, color: "🖤" },
  { id: 10, name: "Media Cadera Vino Tinto", category: "Hasta la Cadera", price: 48000, color: "🍷" },
  { id: 11, name: "Media Compresión Deportiva", category: "Compresión", price: 59000, color: "💙" },
  { id: 12, name: "Media Cadera Encaje Negro", category: "Con Diseño", price: 62000, color: "🖤" },
];

export default function Catalogo() {
  const [active, setActive] = useState("Todos");
  const filtered = active === "Todos" ? products : products.filter((p) => p.category === active);

  return (
    <div className="py-16">
      <div className="container">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-12">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Catálogo</p>
          <h1 className="font-display text-4xl md:text-5xl font-bold text-foreground">Medias para Dama</h1>
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
          {filtered.map((p, i) => (
            <motion.div
              key={p.id}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: i * 0.05 }}
              className="bg-card border border-border rounded-lg overflow-hidden group hover:border-primary/50 transition-all cursor-pointer"
            >
              <div className="h-48 bg-secondary flex items-center justify-center text-5xl group-hover:scale-105 transition-transform">
                {p.color}
              </div>
              <div className="p-4">
                <h3 className="font-medium text-sm text-foreground">{p.name}</h3>
                <p className="text-primary font-bold mt-1">${p.price.toLocaleString("es-CO")}</p>
                <p className="text-muted-foreground text-xs mt-1">{p.category}</p>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
