import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";

export default function SignUp() {
  const router = useRouter();
  const [nombres, setNombres] = useState("");
  const [apellidos, setApellidos] = useState("");
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const onSubmitRegister = async (e) => {
    e.preventDefault();
    console.log(process.env.NEXT_PUBLIC_SERVER_URL);
    const response = await axios.post(`/api/register`, {
      nombres,
      apellidos,
      email,
      username,
      password,
    });
    console.log(response);
    if (response.data) router.push("/");
  };
  return (
    <div className=" bg-blue-200">
      <form onSubmit={onSubmitRegister} className="w-screen h-screen flex flex-col space-y-4 justify-center items-center ">
        <div className="font-mono font-bold underline text-2xl">REGISTER FORM</div>
        <div className="flex flex-col justify-center items-center border-4 px-2 py-4 rounded-lg w-80">
          <div className="flex justify-between w-full ">
            <label className="font-mono font-bold ml-2">Nombres</label>
            <input className="rounded-lg mb-4 pl-3 py-1" onChange={(e) => setNombres(e.currentTarget.value)} value={nombres} />
          </div>
          <div className="flex justify-between w-full">
            <label className="font-mono font-bold ml-2">Apellidos</label>
            <input className="rounded-lg mb-4 pl-3 py-1" onChange={(e) => setApellidos(e.currentTarget.value)} value={apellidos} />
          </div>
          <div className="flex justify-between w-full">
            <label className="font-mono font-bold ml-2">Email</label>
            <input className="rounded-lg mb-4 pl-3 py-1" onChange={(e) => setEmail(e.currentTarget.value)} type="email" value={email} />
          </div>
          <div className="flex justify-between w-full">
            <label className="font-mono font-bold ml-2">UserName</label>
            <input className="rounded-lg mb-4 pl-3 py-1" onChange={(e) => setUsername(e.currentTarget.value)} value={username} />
          </div>
          <div className="flex justify-between w-full">
            <label className="font-mono font-bold ml-2">Password</label>
            <input
              className="rounded-lg mb-4 pl-3 py-1"
              onChange={(e) => setPassword(e.currentTarget.value)}
              type="password"
              value={password}
            />
          </div>
          <hr className="border-gray-200 border-t-4 w-60 mt-4" />
          <button className=" bg-gray-200 rounded-lg w-full py-2 mt-5 font-mono font-bold hover:bg-gray-400" type="submit">
            REGISTER
          </button>
        </div>
      </form>
    </div>
  );
}
