import './App.css'
import {useState} from "react";

function App() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    return (
        <>
            <form>
                <input
                    type="text"
                    value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                />
                <input
                    type="text"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                />
            </form>
        </>
    )
}

export default App
