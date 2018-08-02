x = let
  v = (vrSF &&& vlSF) >>> lift (+)
  t = thetaSF >>> arr cos
  in (v &&& t) >>> lift (*) >>> integral >>> lift (/2)
