import Board from "../components/Board";
import Categories from "../components/Categories";
import Footer from "../components/Footer";
import Logo from "../components/Logo";
import NavBar from "../components/NavBar";
import SearchBar from "../components/SearchBar";

export default function Home() {
  return (
    <div className="w-screen h-screen bg-blue-200">
      <Logo />

      <div className="flex min-h-[83%]">
        <Categories />
        <div className="flex flex-col h-full w-full">
          <NavBar />
          <Board />
        </div>
      </div>
      <Footer />
    </div>
  );
}
export async function getServerSideProps(ctx) {
  const { req, res } = ctx;
  const { cookies } = req;
  const token = cookies.token;
  console.log("cookie", cookies.token);
  if (cookies.token) {
    return {
      redirect: {
        destination: "/login",
        permanent: false,
      },
    };
  }
  return {
    props: { data: "authenticated" },
  };
}
