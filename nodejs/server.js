const express = require('express');
const app = express();

// css 파일 사용하기
app.use(express.static(__dirname + '/public'))
// ejs 라이브러리 사용하기
app.set('view engine', 'ejs')

// mongodb connect
const { MongoClient } = require('mongodb');

let db;
const url = 'mongodb+srv://admin:admin@mongodb-test.iobvixr.mongodb.net/?retryWrites=true&w=majority&appName=mongodb-test';
new MongoClient(url).connect().then((client) => {
  console.log('success DB connect!');
  db = client.db('forum');
  app.listen(8080, () => {
    console.log('https://localhost:8080 에서 서버 실행중');
  });
}).catch((err) => {
  console.log(err);
})

app.get('/', (req, res) => {
  res.send('hi124');
});

app.get('/news', (req, res) => {
  // db에 데이터 입력하기
  db.collection('post').insertOne({ title: '나는 나는 춤을추는 뭉탱이~' })
  res.send('오늘의 뉴스');
});

// 원하는 페이지 보여주기
app.get('/index', (req, res) => {
  res.sendFile(__dirname + '/public/html/index.html');
});

app.get('/list', async (req, res) => {
  // await을 붙이면 오래 걸리는 작업을 다 한 이후에 다름 줄 실행함
  let result = await db.collection('post').find().toArray();
  console.log(result[0].title);
  // res.send('DB에 있던 게시물');
  // views 폴더 안에 있으면 폴더를 안적어 줘도 됨
  res.render('list.ejs', { post: result });
});

app.get('/time', (req, res) => {
  res.render('time.ejs', { time: new Date() })
})

// 글 작성 기능
app.get('/post', (req, res) => {
  res.render('time.ejs', { time: new Date() })
})
