import React from 'react'
import Button from 'emerald-ui/lib/Button';

export const Answer = ({ answer, uid, onDelete, onPlus, onSustract }) => {

 
return <aside className="answer">
  <p>{answer.answer}</p>
  {uid === answer.userId && <Button
    key={answer.id}
    onClick={() => onDelete(answer.id)}
  >Delete</Button>}
   <Button onClick={() => onPlus(answer)}>+1</Button>
  <Button onClick={() => onSustract(answer)}>-1</Button>


</aside>

  }