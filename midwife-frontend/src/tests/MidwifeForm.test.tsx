import MidwifeForm from '../components/MidwifeForm.tsx';
import { render } from '@testing-library/react';

describe('MidwifeForm', () => {
  it('demo', () => {
    expect(1).toBe(1);
  });
  it('should render', () => {
    render(<MidwifeForm />);
  });
});