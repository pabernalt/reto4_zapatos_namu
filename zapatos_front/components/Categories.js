const items = [
  { id: 1, title: "hombres" },
  { id: 2, title: "mujeres" },
  { id: 3, title: "ninos" },
  { id: 4, title: "deportivos" },
  { id: 5, title: "elegantes" },
];
export default function Categories() {
  return (
    <div className="bg-yellow-500 w-52 flex flex-col items-center">
      <div>
        <img src="/images/zapatos_logo.jpg" alt="" className="h-52 " />
      </div>
      <div className="my-4 font-mono font-bold text-2xl ml-2 ">Categories</div>
      {items.map((item, idx) => (
        <CategoryItem key={idx} item={item} />
      ))}
    </div>
  );
}

export function CategoryItem({ item }) {
  return (
    <div className="px-5">
      <button className="bg-yellow-300 w-48 my-2 h-8 rounded-lg font-mono shadow-md font-bold">{item.title}</button>
    </div>
  );
}
