sayHello :: Topic IO S .String
sayHello = repeatM (fmap mkMsg getTime)
where mkMsg = S .String . ("Hi "++) . show

tn :: Node ()
tn = advertise "chatter" (topicRate 1 sayHello)

main :: IO ()
main = runNode "talker" tn

showMsg :: S .String -> IO ()
showMsg = putStrLn . ("Msg: "++) . S.data

main = runNode "listener" $
runHandler showMsg = << subscribe "chatter"