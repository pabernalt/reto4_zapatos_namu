const data = [
  { idx: 0, title: "cafe elegante", src: "/images/hombres/h1.jpg", rate: 7.5, precio: 50000 },
  { idx: 1, title: "prada", src: "/images/mujeres/m1.jpg", rate: 6.8, precio: 90000 },
  { idx: 2, title: "dolce gabbana", src: "/images/hombres/h2.jpg", rate: 10.0, precio: 80000 },
  { idx: 3, title: "invierno", src: "/images/mujeres/m2.jpg", rate: 9.5, precio: 70000 },
  { idx: 4, title: "deportivos", src: "/images/hombres/h3.jpg", rate: 6.9, precio: 540000 },
  { idx: 5, title: "tacones naranja", src: "/images/mujeres/m3.jpg", rate: 8.3, precio: 250000 },
  { idx: 6, title: "jocker", src: "/images/hombres/h4.jpg", rate: 8.4, precio: 251000 },
  { idx: 7, title: "lalaland", src: "/images/mujeres/m4.jpg", rate: 8.5, precio: 55000 },
  { idx: 8, title: "adidas", src: "/images/hombres/h5.jpg", rate: 7.5, precio: 250000 },
  { idx: 9, title: "princessa", src: "/images/mujeres/m5.jpg", rate: 9.1, precio: 150000 },
  { idx: 10, title: "tennis", src: "/images/hombres/h6.jpg", rate: 8.3, precio: 510000 },
  { idx: 11, title: "de entrevista", src: "/images/mujeres/m6.jpg", rate: 8.4, precio: 90000 },
  { idx: 12, title: "aventura", src: "/images/hombres/h7.jpg", rate: 8.5, precio: 55000 },
  { idx: 13, title: "de noche", src: "/images/mujeres/m7.jpg", rate: 7.5, precio: 44000 },
  { idx: 14, title: "nike", src: "/images/hombres/h8.jpg", rate: 9.1, precio: 57000 },
  { idx: 15, title: "prada2", src: "/images/mujeres/m8.jpg", rate: 7.5, precio: 66000 },
  { idx: 16, title: "golf", src: "/images/hombres/h9.jpg", rate: 8.5, precio: 80000 },
  { idx: 17, title: "puma", src: "/images/mujeres/m9.jpg", rate: 7.5, precio: 70000 },
  { idx: 18, title: "fila", src: "/images/hombres/h10.jpg", rate: 9.1, precio: 40000 },
  { idx: 19, title: "yale", src: "/images/mujeres/m10.jpg", rate: 7.5, precio: 650000 },
];
import Image from "next/image";
import { useState } from "react";
import { Checkbox } from "@mui/material";

export function Card({ movie, idx, isClicked, onClickCard }) {
  return (
    <div className="bg-gray-200 rounded-lg">
      <div id="card_div" key={idx} onClick={onClickCard}>
        <div className=" flex flex-col">
          <Image src={movie.src} key={idx} alt="" width="150px" height="150px" />
          <p className="font-mono mt-1 text-center text-blue-700 font-bold underline bg-white w-full ">{movie.title}</p>

          <p className="ml-2 font-mono mt-1">precio : {movie.precio}</p>
          <p className="ml-2 font-mono mt-1">ratio : {movie.rate}</p>
        </div>
      </div>
    </div>
  );
}
export function Container() {
  const [movies, setMovies] = useState(data);
  const [size, setSize] = useState(16);
  const [page, setPage] = useState(0);
  const [input, setInput] = useState("");
  const [clicked, setClicked] = useState([]);
  const lastpage = movies.length === 0 ? 0 : Math.floor((movies.length - 1) / size);
  console.log(lastpage);

  const onClickPrev = () => {
    setPage(page - 1);
  };
  const onClickNext = () => {
    setPage(page + 1);
  };
  const onSubmitSearch = (e) => {
    e.preventDefault();

    const filtered = data.filter((movie) => movie.title.includes(input));

    setInput("");
    setPage(0);
    setMovies(filtered);
  };
  const onClickCard = (e) => {
    console.log(e.currentTarget);
  };

  return (
    <>
      <div className="mt-10">
        <form className="flex space-x-5" onSubmit={onSubmitSearch}>
          <input
            type="text"
            placeholder="Zapatos Nombres"
            className="py-2 px-6 border-2 rounded-full shadow focus:outline-none focus:border-blue-500"
            onChange={(e) => setInput(e.currentTarget.value)}
          />
          <button type="submit" className="py-2 px-6 bg-green-400 border-4 rounded-full shadow text-white font-bold hover:bg-green-800">
            Buscar
          </button>
        </form>
      </div>
      <div
        className="mt-10 grid grid-cols-4 gap-8"
        style={{
          gridTemplateColumns: "150px 150px 150px 150px 150px 150px 150px 150px ",
          gridTemplateRows: "240px 240px",
        }}
      >
        {movies.map((movie, i) =>
          movie.idx < (page + 1) * size && movie.idx >= page * size ? (
            <Card movie={movie} key={i} idx={i} onClickCard={onClickCard} isClicked={clicked.indexOf(movie.idx)} />
          ) : null
        )}
      </div>
      <div className="flex space-x-28 mt-10 mb-8">
        {page === 0 || (
          <button className="px-8 py-3 bg-yellow-500 rounded-xl text-white border-2 border-gray-200" onClick={onClickPrev}>
            PREV
          </button>
        )}
        {page === lastpage || (
          <button className="px-8 py-3 bg-yellow-500 rounded-xl text-white border-2 border-gray-200" onClick={onClickNext}>
            NEXT
          </button>
        )}
      </div>
    </>
  );
}

export default function Board() {
  return (
    <div className="flex flex-col items-center ">
      <Container />
    </div>
  );
}
