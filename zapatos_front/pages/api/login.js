import axios from "axios";
export default async function handler(req, res) {
  console.log("here");
  if (req.method === "POST") {
    console.log(req.body);
    const result = await axios.post(process.env.NEXT_PUBLIC_SERVER_URL + "/api/login", req.body);
    return res.status(200).json(result.data);
  } else {
    // Handle any other HTTP method
  }
}
