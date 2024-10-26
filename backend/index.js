const express= require('express');
const app= express()
const log= require('./middleware/log')

app.use(express.json())
app.use(log)

app.listen(8282,()=>{
    console.log('server is running in port 8282')
})

app.use('/login',require('./routes/login'))
app.use('/restaurant',require('./routes/restaurant'))
app.use('/', require('./routes/routes'))