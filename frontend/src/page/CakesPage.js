import React from 'react'
import ListCakes from '../component/ListCakes'

function CakesPage({setUser,user}) {
  return (
    <>
      <ListCakes setUser={setUser} user={user}/>
    </>
  );
}
export default CakesPage;