import React from "react";
import { FieldError, Path, UseFormRegister } from "react-hook-form";

import { Container, Title, Errostyle } from './styles';

type Props = {
  label: Path<InputsProps>;
  register: UseFormRegister<InputsProps>;
  required: boolean;
  errors: FieldError | undefined;
};

interface InputsProps {
  ID: number;
  Nome: string;
  Sobrenome: string;
  Email: string;
  PIS: number;
};

const Input: React.FC<Props> = ({
  label, register, required, errors
}) => {
  return (
    <>
      <Errostyle>{errors?.message}</Errostyle>
      <Title>{label}</Title>
      <Container>
        <input {...register(label, { required })} />
      </Container>
    </>
  );
};

export default Input;