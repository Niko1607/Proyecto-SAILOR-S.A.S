import { motion } from "framer-motion";
import { Target, Eye, Heart, Award } from "lucide-react";

const values = [
  { icon: Target, title: "Misión", desc: "Diseñar y comercializar medias de alta calidad que realcen la elegancia y confianza de cada mujer." },
  { icon: Eye, title: "Visión", desc: "Ser la marca líder en medias y calzado femenino en Colombia, reconocida por nuestra calidad e innovación." },
  { icon: Heart, title: "Pasión", desc: "Cada media es creada con dedicación y amor, cuidando cada fibra para tu comodidad." },
  { icon: Award, title: "Calidad", desc: "Más de 21 años nos respaldan. Utilizamos los mejores materiales y procesos de manufactura." },
];

export default function Nosotros() {
  return (
    <div className="py-16">
      <div className="container">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-16">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Nuestra Historia</p>
          <h1 className="font-display text-4xl md:text-5xl font-bold text-foreground mb-6">Sobre SAILOR</h1>
          <p className="text-muted-foreground max-w-2xl mx-auto leading-relaxed">
            Con más de 21 años de experiencia en el mundo de la moda, SAILOR S.A.S se ha consolidado como una marca
            que combina elegancia, calidad y confort en cada una de sus prendas. Nacimos con la visión de transformar
            la manera en que las personas se visten, ofreciendo diseños que resaltan su personalidad.
          </p>
        </motion.div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {values.map((v, i) => (
            <motion.div
              key={v.title}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: i * 0.1 }}
              className="bg-card border border-border rounded-lg p-6 text-center hover:border-primary/50 transition-all"
            >
              <v.icon className="h-8 w-8 text-primary mx-auto mb-4" />
              <h3 className="font-display text-lg font-bold text-foreground mb-2">{v.title}</h3>
              <p className="text-muted-foreground text-sm leading-relaxed">{v.desc}</p>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
