x = proc inp -> do
    vr <- vrSF -< inp
    vl <- vlSF -< inp
    theta <- thetaSF -< inp
    i <- integral-< (vr+vl) * cos(theta)
returnA -< (i/2)
