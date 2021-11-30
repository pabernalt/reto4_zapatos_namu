import React from "react";
import { setCookie } from "../custom/cookie";
import { useRouter } from "next/router";
export default function NavBar() {
  const router = useRouter();
  const onClickLogout = () => {
    setCookie("token", null, -1, "/");
    router.push("/login");
  };
  return (
    <div className="bg-[#043249] flex">
      <div className="flex mr-5 ml-3">
        <button className="text-white font-bold font-mono border-l-2 border-r-2 my-4 w-24">Menu1</button>
        <button className="text-white font-bold font-mono  border-r-2 my-4 w-24">Menu2</button>
        <button className="text-white font-bold font-mono  border-r-2 my-4 w-24">Menu3</button>
      </div>
      <div className="ml-auto mr-5 my-4 flex">
        <button className="text-white font-bold font-mono  w-24 underline" onClick={onClickLogout}>
          LogOut
        </button>
      </div>
    </div>
  );
}
