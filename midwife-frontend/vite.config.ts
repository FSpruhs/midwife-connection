import dotenv from 'dotenv';
import { defineConfig } from 'vitest/config';
import react from '@vitejs/plugin-react';

dotenv.config();
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  define: {
    'process.env': process.env,
  },
  test: {
    include: ['**/*.test.ts', '**/*.test.tsx'],
    globals: true,
    environment: 'jsdom',
  },
});
