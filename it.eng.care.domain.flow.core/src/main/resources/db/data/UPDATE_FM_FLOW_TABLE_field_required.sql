update fm_flow_table set required = '1' where section = '0' or section = '1' or section = '2';
update fm_flow_table set required = '1' where section = '3' and (flow = 'c299ad8c-012e-471c-8c0c-ac721fe242cf' or flow = '6c3cc5c2-66d2-4692-8b7b-6140cfdde83c');
update fm_flow_table set required = '0' where section = '4';
update fm_flow_table set required = '0' where section = '3' and (flow = '4d971f52-0716-47bc-9255-5e9ca8f0d028' or flow = 'd4bacbce-0e4f-467d-ba9a-4fd0dbae4e22');