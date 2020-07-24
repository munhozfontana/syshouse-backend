insert
	into
	public.tipodespesa (id, createdat, descricao)
values('6afcf8d0-bbe5-11ea-b3de-0242ac130004', NOW(), 'tipodespesa');

insert
	into
	public.tipopatrimonio (id, createdat, descricao)
values('6afcfd94-bbe5-11ea-b3de-0242ac130004', NOW(), 'tipopatrimonio');

insert
	into
	public.tiporenda (id, createdat, descricao)
values('6afcfec0-bbe5-11ea-b3de-0242ac130004', NOW(), 'tiporenda');

insert
	into
	public.municipio (id, createdat, ibge, nome, pais, populacao, uf)
values('6afd005a-bbe5-11ea-b3de-0242ac130004', NOW(), 0, 'string', 'string', 0, 'uf');

insert
	into
	public.localizacao (id, bairro, cep, complemento, createdat, endereco, latitude, longitude, municipio_id)
values('6afd0154-bbe5-11ea-b3de-0242ac130004', 'string', 'string', 'string', NOW(), 'string', 0, 0, '6afd005a-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.localizacao (id, bairro, cep, complemento, createdat, endereco, latitude, longitude, municipio_id)
values('6afd03de-bbe5-11ea-b3de-0242ac130004', 'string', 'string', 'string', NOW(), 'string', 0, 0, '6afd005a-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.patrimonio (id, createdat, datafim, datainicio, nome, valor, localizacao_id, tipopatrimonio_id)
values('6afd04b0-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), NOW(), 'string', 0, '6afd0154-bbe5-11ea-b3de-0242ac130004', '6afcfd94-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.patrimonio (id, createdat, datafim, datainicio, nome, valor, localizacao_id, tipopatrimonio_id)
values('6afd0578-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), NOW(), 'string', 0, '6afd03de-bbe5-11ea-b3de-0242ac130004', '6afcfd94-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.movimentacao (id, createdat, "data", obs, valor, patrimonioin_id, patrimonioout_id)
values('6afd0640-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), 'string', 0, '6afd04b0-bbe5-11ea-b3de-0242ac130004', '6afd0578-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.midia (id, caminho, createdat, nome, obs, tipo, patrimonio_id)
values('6afd0708-bbe5-11ea-b3de-0242ac130004', 'string', NOW(), 'string', 'string', 'string', '6afd04b0-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.socio (id, cpf, createdat, estadocivil, nacionalidade, nome, profissao, rg)
values('6afd07da-bbe5-11ea-b3de-0242ac130004', 'string', NOW(), 'string', 'string', 'string', 'string', 'string');

insert
	into
	public.sociopatrimonio (id, createdat, porcentagem, proprietario, patrimonio_id, socio_id)
values('6afd08a2-bbe5-11ea-b3de-0242ac130004', NOW(), 0, false, '6afd04b0-bbe5-11ea-b3de-0242ac130004', '6afd07da-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.pagador (id, cnpj, cpf, createdat, endereco, estadocivil, nacionalidade, nascimento, nome, profissao, rg)
values('6afd096a-bbe5-11ea-b3de-0242ac130004', 'string', 'string', NOW(), 'string', 'string', 'string', NOW(), 'string', 'string', 'string');

insert
	into
	public.contato (id, createdat, email, fone, whatsapp, pagador_id, socio_id)
values('6afd0b68-bbe5-11ea-b3de-0242ac130004', NOW(), 'string', 'string', false, '6afd096a-bbe5-11ea-b3de-0242ac130004', '6afd07da-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.dependente (id, createdat, nome, pagador_id)
values('6afd0c3a-bbe5-11ea-b3de-0242ac130004', NOW(), 'string', '6afd096a-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.renda (id, createdat, datafim, datainicio, descricao, obs, valor, vencimento, pagador_id, patrimonio_id, tiporenda_id)
values('6afd0d0c-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), NOW(), 'string', 'string', 0, NOW(), '6afd096a-bbe5-11ea-b3de-0242ac130004', '6afd04b0-bbe5-11ea-b3de-0242ac130004', '6afcfec0-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.recebimento (id, createdat, datarecebimento, obs, valor, renda_id)
values('6afd0dd4-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), 'string', 0, '6afd0d0c-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.recebimentopatrimonio (id, createdat, valorcalculado, patrimonio_id, recebimento_id)
values('6afd0e9c-bbe5-11ea-b3de-0242ac130004', NOW(), 0, '6afd04b0-bbe5-11ea-b3de-0242ac130004', '6afd0dd4-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.despesa (id, createdat, datafim, datainicio, descricao, obs, valor, vencimento, patrimonio_id, tipodespesa_id)
values('6afd1d24-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), NOW(), 'string', 'string', 0, NOW(), '6afd04b0-bbe5-11ea-b3de-0242ac130004', '6afcf8d0-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.pagamento (id, createdat, datapagamento, obs, valor, despesa_id)
values('6afd206c-bbe5-11ea-b3de-0242ac130004', NOW(), NOW(), 'string', 0, '6afd1d24-bbe5-11ea-b3de-0242ac130004');

insert
	into
	public.pagamentopatrimonio (id, createdat, valorcalculado, pagamento_id, patrimonio_id)
values('6afd2166-bbe5-11ea-b3de-0242ac130004', NOW(), 0, '6afd206c-bbe5-11ea-b3de-0242ac130004', '6afd04b0-bbe5-11ea-b3de-0242ac130004');





