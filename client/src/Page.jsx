import axios from "axios";
import React from "react";
import { useState } from "react";
import "./Page.css";

function Page() {
  const [query, setQuery] = useState("");
  const [books, setBooks] = useState([]);
  const [found, setFound] = useState(false);
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [description, setDescription] = useState("");

  const handleInput = (event) => {
    setQuery(event.target.value);
  };

  const submitSearch = () => {
    axios({
      method: "get",
      url: "http://localhost:8080/book",
      params: { query: query },
    }).then((resp) => {
      if (resp.data.length > 0) {
        setBooks(resp.data);
        setFound(true);
      }
    });
  };

  const getAllBooks = () => {
    setBooks([]);
    setFound(false);
    axios({
      method: "get",
      url: "http://localhost:8080/books",
    }).then((resp) => {
      if (resp.data.length > 0) {
        setBooks(resp.data);
        setFound(true);
      }
    });
  };

  const getTitle = (event) => {
    setTitle(event.target.value);
  };

  const getAuthor = (event) => {
    setAuthor(event.target.value);
  };

  const getDescription = (event) => {
    setDescription(event.target.value);
  };

  const saveBook = () => {
    if (title !== "" || author !== "" || description !== "") {
      const book = { title: title, author: author, description: description };
      axios({
        method: "post",
        url: "http://localhost:8080/book",
        data: book,
      }).then(() => {
        setTitle("");
        setAuthor("");
        setDescription("");
      });
    } else {
      alert("Please fill out all the fields");
    }
  };

  return (
    <div className="page">
      <h1>EXAMEN</h1>
      <div className="searchInput">
        Search
        <input type="text" onChange={handleInput} value={query} />
        <button onClick={submitSearch}>Search</button>
        <br />
        <button onClick={getAllBooks}>Get All</button>
        {found && (
          <table>
            <tr>
              <th>Title</th>
              <th>Description</th>
              <th>Author</th>
            </tr>
            {books.map((book) => (
              <tr>
                <td key={book.id}>{book.title} </td>
                <td key={book.description}>{book.description}</td>
                <td key={book.author}> {book.author}</td>
              </tr>
            ))}
          </table>
        )}
      </div>
      <div className="inputArea">
        <h4>Add a new book</h4>
        Author:
        <input type="text" onChange={getAuthor} value={author} />
        <br />
        Title:
        <input type="text" onChange={getTitle} value={title} />
        <br />
        Description:
        <input type="text" onChange={getDescription} value={description} />
        <br />
        <button onClick={saveBook}>Save</button>
      </div>
    </div>
  );
}

export default Page;
