import { motion } from "framer-motion";
import collectionImg from "@/assets/collection-preview.jpg";

const collections = [
  { name: "Primavera 2026", desc: "Colores vibrantes y telas ligeras para la nueva temporada.", tag: "Nueva" },
  { name: "Street Urban", desc: "Estilo urbano con actitud. Hoodies, cargo pants y sneakers.", tag: "Popular" },
  { name: "Clásicos SAILOR", desc: "Las prendas icónicas que definen nuestra marca desde hace 21 años.", tag: "Edición Especial" },
];

export default function Colecciones() {
  return (
    <div className="py-16">
      <div className="container">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-12">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Temporadas</p>
          <h1 className="font-display text-4xl md:text-5xl font-bold text-foreground">Colecciones</h1>
        </motion.div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {collections.map((c, i) => (
            <motion.div
              key={c.name}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: i * 0.1 }}
              className="relative rounded-xl overflow-hidden group cursor-pointer"
            >
              <img src={collectionImg} alt={c.name} className="w-full h-80 object-cover group-hover:scale-105 transition-transform duration-500" loading="lazy" width={1024} height={768} />
              <div className="absolute inset-0 bg-background/70 flex flex-col items-center justify-center p-6 text-center">
                <span className="text-primary text-xs tracking-widest font-semibold uppercase mb-2">{c.tag}</span>
                <h3 className="font-display text-2xl font-bold text-foreground mb-2">{c.name}</h3>
                <p className="text-muted-foreground text-sm">{c.desc}</p>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
