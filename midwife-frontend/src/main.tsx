import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import { ApolloClient, InMemoryCache, ApolloProvider } from '@apollo/client';
import { BrowserRouter } from 'react-router-dom';

const client = new ApolloClient({
  uri: process.env.VITE_BACKEND_URI,
  cache: new InMemoryCache(),
});

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ApolloProvider client={client}>
      <BrowserRouter>
          <App />
      </BrowserRouter>
    </ApolloProvider>
  </React.StrictMode>,
);
