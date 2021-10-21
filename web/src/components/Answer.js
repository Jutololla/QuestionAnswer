import React from 'react'
import Button from 'emerald-ui/lib/Button';

export const Answer = ({ answer, uid, onDelete }) => {

 
return <aside className="answer">
  <p>{answer.answer}</p>
  {uid === answer.userId && <Button
    key={answer.id}
    onClick={() => onDelete(answer.id)}
  >Delete</Button>}


</aside>

  }