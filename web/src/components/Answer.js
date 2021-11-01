import React from 'react'
import Button from 'emerald-ui/lib/Button';

export const Answer = ({ answer, uid, onDelete, onPlus, onSustract }) => {

 
return <aside className="answer">
  <div dangerouslySetInnerHTML={{__html:answer.answer}} />
  {uid === answer.userId && <Button
    key={answer.id}
    onClick={() => onDelete(answer.id)}
  >Delete</Button>}

{uid&&<>
   <Button onClick={() => onPlus(answer)}>+1</Button>
  <Button onClick={() => onSustract(answer)}>-1</Button>
  </>
}

  <>   {answer.position}</>


</aside>

  }