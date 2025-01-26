
import React, { useEffect } from "react";
import { useNavigate , useParams } from "react-router-dom";

const Redirect = () => {
  const navigate = useNavigate ();
  const { id } = useParams();
  useEffect(() => {
    navigate(`/restaurant/${id}/overview`);
  }, []);

  return <div></div>;
};

export default Redirect;
