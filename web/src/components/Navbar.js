import React from 'react'
import { Link } from 'react-router-dom'

export const PublicNavbar = () => (
  <nav>
    <section>
      <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/QA_icon_clr.svg/120px-QA_icon_clr.svg.png" 
      alt="icon"
      width="40" 
     height="40" />
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
    </section>
  </nav>
)

export const PrivateNavbar = () => (
  <nav>
    <section>
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/QA_icon_clr.svg/120px-QA_icon_clr.svg.png" 
    alt="icon"
    width="40" 
     height="40"/>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
      <Link to="/new">New</Link>
      <Link to="/list">List</Link>
    </section>
  </nav>
)
