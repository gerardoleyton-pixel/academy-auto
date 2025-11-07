const express = require('express');
const path = require('path');
const app = express();

app.use(express.static(path.join(__dirname, 'build/serenity')));

const port = 3000;
app.listen(port, () => {
  console.log(`Serenity report server running at http://localhost:${port}`);
});