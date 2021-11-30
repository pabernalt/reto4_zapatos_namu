import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import { setCookie } from "../custom/cookie";

export default function SignIn() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const onSubmitLogin = async (e) => {
    e.preventDefault();
    console.log(process.env.NEXT_PUBLIC_SERVER_URL);
    const response = await axios.post(`/api/login`, {
      username,
      password,
    });

    if (response.data.token) {
      setCookie("token", response.data.token, {
        path: "/",
        secure: true,
        sameSite: "none",
      });
      router.push("/");
    }
  };
  return (
    <div className=" bg-blue-200">
      <form onSubmit={onSubmitLogin} className="w-screen h-screen flex flex-col space-y-4 justify-center items-center ">
        <div className="font-mono font-bold underline text-2xl">LOGIN FORM</div>
        <div className="flex flex-col justify-center items-center border-4 px-2 py-4 rounded-lg w-80">
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
            LOGIN
          </button>
        </div>
      </form>
    </div>
  );
}
