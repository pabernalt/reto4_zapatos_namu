const data = [
  { idx: 0, title: "adventure", src: "/images/adventure.jpg", rate: 7.5 },
  { idx: 1, title: "aladin", src: "/images/aladin.jpg", rate: 6.8 },
  { idx: 2, title: "avatar", src: "/images/avatar.jpg", rate: 10.0 },
  { idx: 3, title: "avengers", src: "/images/avengers.jpg", rate: 9.5 },
  { idx: 4, title: "blackwidow", src: "/images/blackwidow.jpg", rate: 6.9 },
  { idx: 5, title: "et", src: "/images/et.jpg", rate: 8.3 },
  { idx: 6, title: "jocker", src: "/images/jocker.jpg", rate: 8.4 },
  { idx: 7, title: "lalaland", src: "/images/lalaland.jpg", rate: 8.5 },
  { idx: 8, title: "moonlight", src: "/images/moonlight.jpg", rate: 7.5 },
  { idx: 9, title: "titanic", src: "/images/titanic.jpg", rate: 9.1 },
];
import Image from "next/image";
import { useState } from "react";

export function Card({ movie, idx }) {
  const onClickCard = (e) => {
    console.log(e.currentTarget.style.transform);
    if (e.currentTarget.style.transform === "rotateY(180deg)") e.currentTarget.style.transform = "rotateY(0deg)";
    else e.currentTarget.style.transform = "rotateY(180deg)";
  };
  return (
    <div style={{ width: "150px", height: "220px", perspective: "1000px" }}>
      <div
        id="card_div"
        style={{
          width: "100%",
          height: "100%",
          transition: "transform 0.5s",
          transformStyle: "preserve-3d",
        }}
        onClick={onClickCard}
      >
        <div
          style={{
            position: "absolute",
            backfaceVisibility: "hidden",
          }}
        >
          <Image src={movie.src} key={idx} alt="" width="150px" height="220px" />
        </div>
        <div
          style={{
            transform: "rotateY(180deg)",
            position: "absolute",
            backfaceVisibility: "hidden",
          }}
        >
          <Image src="/images/blank.jpg" key={idx} alt="" width="150px" height="220px" />
        </div>
      </div>
    </div>
  );
}
export function Container() {
  const [movies, setMovies] = useState(data);
  const [size, setSize] = useState(8);
  const [page, setPage] = useState(0);
  const [input, setInput] = useState("");
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

  return (
    <>
      <div>
        <form className="flex space-x-5" onSubmit={onSubmitSearch}>
          <input
            type="text"
            placeholder="Movie Title"
            className="py-2 px-6 border-2 rounded-full shadow focus:outline-none focus:border-blue-500"
            onChange={(e) => setInput(e.currentTarget.value)}
          />
          <button type="submit" className="py-2 px-6 bg-green-400 border-4 rounded-full shadow text-white font-bold hover:bg-green-800">
            Search
          </button>
        </form>
      </div>
      <div
        className="mt-10 grid grid-cols-4 gap-4"
        style={{
          gridTemplateColumns: "150px 150px 150px 150px",
          gridTemplateRows: "220px 220px",
        }}
      >
        {movies.map((movie, i) =>
          movie.idx < (page + 1) * size && movie.idx >= page * size ? <Card movie={movie} key={i} idx={i} /> : null
        )}
      </div>
      <div className="flex space-x-4 mt-4">
        {page === 0 ? (
          <button className="px-3 py-3 bg-red-500 rounded-xl text-white border-2 border-gray-200 opacity-30">PREV</button>
        ) : (
          <button className="px-3 py-3 bg-red-500 rounded-xl text-white border-2 border-gray-200" onClick={onClickPrev}>
            PREV
          </button>
        )}
        {page === lastpage ? (
          <button className="px-3 py-3 bg-red-500 rounded-xl text-white border-2 border-gray-200 opacity-30">NEXT</button>
        ) : (
          <button className="px-3 py-3 bg-red-500 rounded-xl text-white border-2 border-gray-200" onClick={onClickNext}>
            NEXT
          </button>
        )}
      </div>
    </>
  );
}

export default function Filter() {
  return (
    <div className="flex flex-col w-screen h-screen items-center bg-blue-200">
      <div className="mt-6 mb-6 bg-gray-50 border-indigo-400 border-4 rounded-lg px-4 py-2 text-4xl font-semibold font-mono">
        <h2>Movie Chart</h2>
      </div>

      <Container />
    </div>
  );
}
