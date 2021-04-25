import React from 'react';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';

import { Container, Formcontent, Buttonform } from './styles';


import Input from '../components/Input';

type InputsProps = {
  ID: number;
  Nome: string;
  Sobrenome: string;
  Email: string;
  PIS: number;
};

const schema = yup.object().shape({
  ID: yup.number().min(1),
  Nome: yup.string().required('Preencha o campo nome'),
  Sobrenome: yup.string().required('Preencha o campo sobrenome'),
  Email: yup
    .string()
    .required('Preencha o campo email')
    .email('Informe um e-mail valido'),
  PIS: yup.number().min(11).max(11),
});

export default function App() {
  const { register, handleSubmit, formState: { errors } } = useForm<InputsProps>({
    resolver: yupResolver(schema),
  });
  const onSubmit = (data: InputsProps) => console.log(data);

  return (
    <Container>
      <Formcontent>
        <h1>Cadastro de Funcionários</h1>
        <form onSubmit={handleSubmit(onSubmit)}>
        <Input label="ID" register={register} required errors={errors.ID}/>

          <Input label="Nome" register={register} required errors={errors.Nome}/>

          <Input label="Sobrenome" register={register} required errors={errors.Sobrenome}/>

          <Input label="Email" register={register} required errors={errors.Email}/>
          
          <Input label="PIS" register={register} required errors={errors.PIS}/>

          <Buttonform type="submit">Enviar</Buttonform>
        </form>
      </Formcontent>
    </Container>
  );
}