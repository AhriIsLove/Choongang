interface Todo {
  tno: number
  title: string
  writer: string
  due_date: string | null 
  complete: boolean
}


interface TodoAdd {
  title: string,
  writer: string,
  due_date: string 
}

interface TodoModify {
  tno: number,
  title: string,
  due_date: string | null,
  complete: boolean
}
  
  